package jp.groupsession.v2.sch.sch094;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchPriConfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール一覧表示設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch094Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch094Biz.class);
    /** リクエスト */
    private RequestModel reqMdl__ = null;
    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Sch094Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch094ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Sch094ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());

        //件数
        if (paramMdl.getSch094DefLine() < 0) {
            paramMdl.setSch094DefLine(pconf.getSccDspList());
        }

        //自動リロード時間
        paramMdl.setSch094ReloadTime(NullDefault.getString(
                paramMdl.getSch094ReloadTime(), String.valueOf(pconf.getSccReload())));
        paramMdl.setSch094TimeLabelList(__getTimeLabel());

        //週間表示開始曜日
        paramMdl.setSch094SelWeek(NullDefault.getString(
                paramMdl.getSch094SelWeek(), String.valueOf(pconf.getSccIniWeek())));
        paramMdl.setSch094WeekList(__getWeekLabel());

    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch094ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Sch094ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());

        //件数
        pconf.setSccDspList(paramMdl.getSch094DefLine());
        pconf.setSccReload(NullDefault.getInt(
                paramMdl.getSch094ReloadTime(), GSConstSchedule.AUTO_RELOAD_10MIN));
        pconf.setSccIniWeek(NullDefault.getInt(
                paramMdl.getSch094SelWeek(), GSConstSchedule.CHANGE_WEEK_TODAY));

        pconf.setSccEuid(umodel.getUsrsid());
        pconf.setSccEdate(new UDate());

        boolean commitFlg = false;
        try {
            SchPriConfDao dao = new SchPriConfDao(con);
            int count = dao.updateListDisp(pconf);
            if (count <= 0) {
                //レコードがない場合は作成
                dao.insert(pconf);
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

    /**
     * <br>[機  能] 自動リロード時間コンボの表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @return labelList ラベルリスト
     */
    private List <LabelValueBean> __getTimeLabel() {
        List <LabelValueBean> labelList = new ArrayList <LabelValueBean>();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ 分 **/
        String minute = gsMsg.getMessage("cmn.minute");
        /** メッセージ リロードしない **/
        String noReroad = gsMsg.getMessage("cmn.without.reloading");

        labelList.add(new LabelValueBean("1" + minute, "60000"));
        labelList.add(new LabelValueBean("3" + minute, "180000"));
        labelList.add(new LabelValueBean("5" + minute, "300000"));
        labelList.add(new LabelValueBean("10" + minute, "600000"));
        labelList.add(new LabelValueBean("20" + minute, "1200000"));
        labelList.add(new LabelValueBean("30" + minute, "1800000"));
        labelList.add(new LabelValueBean("40" + minute, "2400000"));
        labelList.add(new LabelValueBean("50" + minute, "3000000"));
        labelList.add(new LabelValueBean("60" + minute, "3600000"));
        labelList.add(new LabelValueBean(noReroad, "0"));
        return labelList;
    }

    /**
     * <br>[機  能] 週間表示開始曜日コンボの表示を設定する。
     * <br>[解  説]
     * <br>[備  考]
     * @return labelList ラベルリスト
     */
    private List <LabelValueBean> __getWeekLabel() {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //今日
        String textToday = gsMsg.getMessage("cmn.today");
        //木曜日
        String textThursday = gsMsg.getMessage("cmn.thursday3");
        //金曜日
        String textFriday = gsMsg.getMessage("schedule.src.101");
        //土曜日
        String textSaturday = gsMsg.getMessage("cmn.saturday3");
        //日曜日
        String textSunday = gsMsg.getMessage("cmn.sunday3");
        //月曜日
        String textMonday = gsMsg.getMessage("cmn.monday3");
        //火曜日
        String textTuesday = gsMsg.getMessage("cmn.tuesday3");
        //水曜日
        String textWednesday = gsMsg.getMessage("cmn.wednesday3");

        List <LabelValueBean> labelList = new ArrayList <LabelValueBean>();
        labelList.add(new LabelValueBean(textToday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_TODAY)));
        labelList.add(new LabelValueBean(textSunday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_SUN)));
        labelList.add(new LabelValueBean(textMonday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_MON)));
        labelList.add(new LabelValueBean(textTuesday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_TUE)));
        labelList.add(new LabelValueBean(textWednesday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_WED)));
        labelList.add(new LabelValueBean(textThursday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_THU)));
        labelList.add(new LabelValueBean(textFriday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_FRI)));
        labelList.add(new LabelValueBean(textSaturday,
                String.valueOf(GSConstSchedule.CHANGE_WEEK_SAT)));
        return labelList;
    }
}
