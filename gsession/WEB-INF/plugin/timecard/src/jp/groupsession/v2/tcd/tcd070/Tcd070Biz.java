package jp.groupsession.v2.tcd.tcd070;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstTimecard;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.SaibanModel;
import jp.groupsession.v2.cmn.model.base.TcdAdmConfModel;
import jp.groupsession.v2.tcd.TimecardBiz;
import jp.groupsession.v2.tcd.TimecardUtil;
import jp.groupsession.v2.tcd.dao.TcdTimezoneDao;
import jp.groupsession.v2.tcd.dao.TcdTimezoneModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] タイムカード 管理者設定 時間帯設定登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Tcd070Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Tcd070Biz.class);

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param reqMdl リクエスト情報
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Tcd070ParamModel paramMdl,
            RequestModel reqMdl, Connection con) throws SQLException {
        log__.debug("時間帯設定登録");

        //セッション情報を取得
        BaseUserModel usModel = reqMdl.getSmodel();
        int sessionUsrSid = usModel.getUsrsid();

        //DBより設定情報を取得。なければデフォルト値とする。
        TimecardBiz tcdBiz = new TimecardBiz(reqMdl);
        //管理者設定
        TcdAdmConfModel admMdl = tcdBiz.getTcdAdmConfModel(sessionUsrSid, con);
        paramMdl.setTcd070ZoneLabel(tcdBiz.getZoneLabelList());
        paramMdl.setTcd070HourLabel(tcdBiz.getHourLabelList(24));
        paramMdl.setTcd070MinuteLabel(tcdBiz.getMinLabelList(admMdl));
        //DB値
        if (!StringUtil.isNullZeroStringSpace(paramMdl.getEditTimezoneSid())) {
            TcdTimezoneDao tzDao = new TcdTimezoneDao(con);
            int sid = Integer.parseInt(paramMdl.getEditTimezoneSid());
            TcdTimezoneModel tzMdl = tzDao.select(sid);
            Time frTime = tzMdl.getTtzFrtime();
            Time toTime = tzMdl.getTtzTotime();
            String frHour = String.valueOf(TimecardUtil.getTimeHour(frTime));
            String frMin = String.valueOf(TimecardUtil.getTimeMin(frTime));
            String toHour = null;
            log__.debug("frTime.compareTo(toTime)==>" + frTime.compareTo(toTime));
            if (frTime.compareTo(toTime) >= UDate.EQUAL) {
                toHour = String.valueOf(TimecardUtil.getTimeHour(toTime) + 24);
            } else {
                toHour = String.valueOf(TimecardUtil.getTimeHour(toTime));
            }
            String toMin = String.valueOf(TimecardUtil.getTimeMin(toTime));

            paramMdl.setTcd070ZoneKbn(NullDefault.getString(
                    paramMdl.getTcd070ZoneKbn(),
                    String.valueOf(tzMdl.getTtzKbn())));
            paramMdl.setTcd070FrH(NullDefault.getString(
                    paramMdl.getTcd070FrH(),
                    frHour));
            paramMdl.setTcd070FrM(NullDefault.getString(
                    paramMdl.getTcd070FrM(),
                    frMin));
            paramMdl.setTcd070ToH(NullDefault.getString(
                    paramMdl.getTcd070ToH(),
                    toHour));
            paramMdl.setTcd070ToM(NullDefault.getString(
                    paramMdl.getTcd070ToM(),
                    toMin));
        } else {
            paramMdl.setTcd070ZoneKbn(NullDefault.getString(
                    paramMdl.getTcd070ZoneKbn(),
                    paramMdl.getAddTimezoneKbn()));
            paramMdl.setTcd070FrH(NullDefault.getString(
                    paramMdl.getTcd070FrH(),
                    String.valueOf(GSConstTimecard.DF_IN_TIME)));
            paramMdl.setTcd070FrM(NullDefault.getString(
                    paramMdl.getTcd070FrM(),
                    String.valueOf(GSConstTimecard.DF_IN_MIN)));
            paramMdl.setTcd070ToH(NullDefault.getString(
                    paramMdl.getTcd070ToH(),
                    String.valueOf(GSConstTimecard.DF_OUT_TIME)));
            paramMdl.setTcd070ToM(NullDefault.getString(
                    paramMdl.getTcd070ToM(),
                    String.valueOf(GSConstTimecard.DF_OUT_MIN)));
        }
    }

    /**
     * <br>[機  能] 時間帯設定を登録・更新します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param cntCon MlCountMtController
     * @param con コネクション
     * @return int 更新件数
     * @throws SQLException SQL実行時例外
     */
    public int commitTimeZone(
            Tcd070ParamModel paramMdl,
            int usrSid,
            MlCountMtController cntCon,
            Connection con) throws SQLException {
        int ret = 0;
        if (!StringUtil.isNullZeroStringSpace(paramMdl.getAddTimezoneKbn())) {
            //登録
            insertTimeZone(paramMdl, usrSid, cntCon, con);
        } else {
            //更新
            updateTimeZone(paramMdl, usrSid, con);
        }
        return ret;
    }

    /**
     * <br>[機  能] 時間帯設定を更新します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return int 更新件数
     * @throws SQLException SQL実行時例外
     */
    public int updateTimeZone(Tcd070ParamModel paramMdl, int usrSid, Connection con)
    throws SQLException {
        int ret = 0;
        TcdTimezoneModel tzMdl = new TcdTimezoneModel();
        tzMdl.setTtzSid(Integer.parseInt(paramMdl.getEditTimezoneSid()));
        //登録値設定
        tzMdl.setTtzKbn(Integer.parseInt(paramMdl.getTcd070ZoneKbn()));
        UDate frDate = new UDate();
        frDate.setHour(Integer.parseInt(paramMdl.getTcd070FrH()));
        frDate.setMinute(Integer.parseInt(paramMdl.getTcd070FrM()));
        frDate.setSecond(0);
        UDate toDate = new UDate();
        toDate.setHour(Integer.parseInt(paramMdl.getTcd070ToH()));
        toDate.setMinute(Integer.parseInt(paramMdl.getTcd070ToM()));
        toDate.setSecond(0);
        Time frTime = new Time(frDate.getTime());
        Time toTime = new Time(toDate.getTime());
        tzMdl.setTtzFrtime(frTime);
        tzMdl.setTtzTotime(toTime);
        TcdTimezoneDao dao = new TcdTimezoneDao(con);
        ret = dao.update(tzMdl);
        return ret;
    }

    /**
     * <br>[機  能] 時間帯設定を登録します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param cntCon MlCountMtController
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void insertTimeZone(
            Tcd070ParamModel paramMdl,
            int usrSid,
            MlCountMtController cntCon,
            Connection con) throws SQLException {

        TcdTimezoneModel tzMdl = new TcdTimezoneModel();
        //時間帯SID採番
        int sid = (int) cntCon.getSaibanNumber(SaibanModel.SBNSID_TIMECARD,
                SaibanModel.SBNSID_SUB_TCD, usrSid);
        tzMdl.setTtzSid(sid);
        //登録値設定
        tzMdl.setTtzKbn(Integer.parseInt(paramMdl.getTcd070ZoneKbn()));
        UDate frDate = new UDate();
        frDate.setHour(Integer.parseInt(paramMdl.getTcd070FrH()));
        frDate.setMinute(Integer.parseInt(paramMdl.getTcd070FrM()));
        frDate.setSecond(0);
        UDate toDate = new UDate();
        toDate.setHour(Integer.parseInt(paramMdl.getTcd070ToH()));
        toDate.setMinute(Integer.parseInt(paramMdl.getTcd070ToM()));
        toDate.setSecond(0);
        Time frTime = new Time(frDate.getTime());
        Time toTime = new Time(toDate.getTime());
        tzMdl.setTtzFrtime(frTime);
        tzMdl.setTtzTotime(toTime);
        //更新
        TcdTimezoneDao dao = new TcdTimezoneDao(con);
        dao.insert(tzMdl);
    }

    /**
     * <br>[機  能] 時間帯を削除します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return int 更新件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteTimeZone(
            Tcd070ParamModel paramMdl,
            int usrSid,
            Connection con) throws SQLException {
        int ret = 0;
        if (StringUtil.isNullZeroStringSpace(paramMdl.getAddTimezoneKbn())) {
            int sid = Integer.parseInt(paramMdl.getEditTimezoneSid());
            //削除
            TcdTimezoneDao dao = new TcdTimezoneDao(con);
            ret = dao.delete(sid);
        }
        return ret;
    }

}
