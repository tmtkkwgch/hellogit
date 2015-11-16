package jp.groupsession.v2.sch.sch050;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.sch.sch010.Sch010Biz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール個人設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch050Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Sch050Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>初期表示画面情報を取得します
     * @param paramMdl Sch050ParamModel
     * @param con コネクション
     * @return アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Sch050ParamModel getInitData(Sch050ParamModel paramMdl, Connection con)
    throws SQLException {

        //セッション情報を取得
        BaseUserModel usModel = reqMdl__.getSmodel();
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        Sch010Biz sch010biz = new Sch010Biz(reqMdl__);
        //個人設定を取得
        SchPriConfModel confMdl = sch010biz.getPrivateConf(sessionUsrSid, con);
        UDate confFrom = confMdl.getSccFrDate();
        UDate confTo = confMdl.getSccToDate();

        //リクエストパラメータを取得
        String frHour = NullDefault.getString(
                paramMdl.getSch050FrHour(), String.valueOf(confFrom.getIntHour()));
        String frMin = NullDefault.getString(
                paramMdl.getSch050FrMin(), String.valueOf(confFrom.getIntMinute()));
        String toHour = NullDefault.getString(
                paramMdl.getSch050ToHour(), String.valueOf(confTo.getIntHour()));
        String toMin = NullDefault.getString(
                paramMdl.getSch050ToMin(), String.valueOf(confTo.getIntMinute()));

        paramMdl.setSch050FrHour(frHour);
        paramMdl.setSch050FrMin(frMin);
        paramMdl.setSch050ToHour(toHour);
        paramMdl.setSch050ToMin(toMin);
        //コンボボックスを設定
        paramMdl.setSch050HourLabel(getHourLabel());
        paramMdl.setSch050MinuteLabel(getMinuteLabel());

        return paramMdl;
    }

    /**
     * <br>時コンボを生成します
     * @return ArrayList (in LabelValueBean)  時コンボ
     */
    public ArrayList<LabelValueBean> getHourLabel() {
        int hour = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        //時
        for (int i = 0; i < 24; i++) {
            labelList.add(
                    new LabelValueBean(String.valueOf(hour), String.valueOf(hour)));
            hour++;
        }
        return labelList;
    }

    /**
     * <br>分コンボを生成します
     * @return ArrayList (in LabelValueBean)  分コンボ
     */
    public ArrayList<LabelValueBean> getMinuteLabel() {
        int min = 0;
        //分
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 4; i++) {
            labelList.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(min, "00"), String.valueOf(min)));
            min = min + 15;
        }
        return labelList;
    }

    /**
     * <br>スケジュールを更新します
     * @param paramMdl Sch050ParamModel
     * @param userSid ユーザSID
     * @param con コネクション
     * @return 更新件数
     * @throws SQLException SQL実行時例外
     */
    public int updateScheduleConf(Sch050ParamModel paramMdl,
            int userSid,
            Connection con) throws SQLException {
        log__.debug("更新処理開始");

        int cnt = 0;
        //パラメータ値取得
        int frHour = NullDefault.getInt(paramMdl.getSch050FrHour(), GSConstSchedule.DF_FROM_HOUR);
        int frMin = NullDefault.getInt(paramMdl.getSch050FrMin(), GSConstSchedule.DF_FROM_MINUTES);
        int toHour = NullDefault.getInt(paramMdl.getSch050ToHour(), GSConstSchedule.DF_TO_HOUR);
        int toMin = NullDefault.getInt(paramMdl.getSch050ToMin(), GSConstSchedule.DF_TO_MINUTES);

        SchPriConfDao confDao = new SchPriConfDao(con);
        SchPriConfModel scMdl = new SchPriConfModel();
        UDate frDate = new UDate();
        frDate.setHour(frHour);
        frDate.setMinute(frMin);
        frDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        frDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        UDate toDate = new UDate();
        toDate.setHour(toHour);
        toDate.setMinute(toMin);
        toDate.setSecond(GSConstSchedule.DAY_START_SECOND);
        toDate.setMilliSecond(GSConstSchedule.DAY_START_MILLISECOND);
        //更新モデルへ設定
        scMdl.setUsrSid(userSid);
        scMdl.setSccFrDate(frDate);
        scMdl.setSccToDate(toDate);
        scMdl.setSccEuid(userSid);
        scMdl.setSccEdate(new UDate());
        cnt = confDao.updateFromTo(scMdl);

        log__.debug("更新処理終了");
        return cnt;
    }
}
