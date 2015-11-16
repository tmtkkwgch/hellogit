package jp.groupsession.v2.sch.sch091;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.sch.dao.SchColMsgDao;
import jp.groupsession.v2.sch.dao.SchPriConfDao;
import jp.groupsession.v2.sch.model.SchAdmConfModel;
import jp.groupsession.v2.sch.model.SchPriConfModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] スケジュール 個人設定 初期値設定画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Sch091Biz {
    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Sch091Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl RequestModel
     */
    public Sch091Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch091ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(Sch091ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        SchCommonBiz biz = new SchCommonBiz(reqMdl__);

        //管理者設定を取得
        SchAdmConfModel admMdl = new SchAdmConfModel();
        admMdl = biz.getAdmConfModel(con);
        paramMdl.setSch091colorKbn(admMdl.getSadMsgColorKbn());

        //DBより設定情報を取得。なければデフォルト値とする。
        if (paramMdl.getSch091initFlg() != 1) {
            SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());
            UDate ifr = pconf.getSccIniFrDate();
            UDate ito = pconf.getSccIniToDate();

            //開始 時
            log__.debug("開始 時 = " + ifr.getIntHour());
            paramMdl.setSch091DefFrH(ifr.getIntHour());
            //開始 分
            log__.debug("開始 分 = " + ifr.getIntMinute());
            paramMdl.setSch091DefFrM(ifr.getIntMinute());
            //終了 時
            log__.debug("終了 時 = " + ito.getIntHour());
            paramMdl.setSch091DefToH(ito.getIntHour());
            //終了 分
            log__.debug("終了 分 = " + ito.getIntMinute());
            paramMdl.setSch091DefToM(ito.getIntMinute());

            //公開フラグ
            paramMdl.setSch091PubFlg(pconf.getSccIniPublic());
            //編集権限
            paramMdl.setSch091Edit(pconf.getSccIniEdit());
            //タイトルカラー
            paramMdl.setSch091Fcolor(String.valueOf(
                    biz.getUserColor(pconf.getSccIniFcolor(), con)));
            //同時修正
            paramMdl.setSch091Same(pconf.getSccIniSame());


            paramMdl.setSch091initFlg(1);
        }
        //画面表示情報を設定
        setDspData(paramMdl, con);

        //タイトルカラーコメント
        setTitleColorMsg(paramMdl, con);
    }

    /**
     * <br>[機  能] 画面表示に必要な情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setDspData(Sch091ParamModel paramMdl, Connection con) throws SQLException {
        //ラベル(時)
        ArrayList<LabelValueBean> sch091HourLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 24; i++) {
            sch091HourLabel.add(new LabelValueBean(String.valueOf(i), Integer.toString(i)));
        }
        paramMdl.setSch091HourLabel(sch091HourLabel);
        //ラベル(分)
        SchCommonBiz schCmnBiz = new SchCommonBiz(reqMdl__);
        int hourMemCount = schCmnBiz.getDayScheduleHourMemoriMin(con);
        ArrayList<LabelValueBean> sch091MinuteLabel = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 60; i += hourMemCount) {
            sch091MinuteLabel.add(
                    new LabelValueBean(StringUtil.toDecFormat(i, "00"), Integer.toString(i)));
        }
        paramMdl.setSch091MinuteLabel(sch091MinuteLabel);

        //編集権限、公開区分 編集許可を設定
        paramMdl.setSch091EditFlg(schCmnBiz.canEditInitConf(con));
        paramMdl.setSch091PublicFlg(schCmnBiz.canPubInitConf(con));
        paramMdl.setSch091SameFlg(schCmnBiz.canSameInitConf(con));
    }

    /**
     * タイトルカラーコメントを設定します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    public void setTitleColorMsg(Sch091ParamModel paramMdl, Connection con) throws SQLException {

        //タイトルカラーコメント
        SchColMsgDao msgDao = new SchColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        paramMdl.setSch091ColorMsgList(msgList);
    }

    /**
     * <br>[機  能] 設定された個人設定情報をDBに保存する。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch091ParamModel
     * @param umodel ユーザ基本情報モデル
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setPconfSetting(Sch091ParamModel paramMdl,
            BaseUserModel umodel, Connection con) throws SQLException {

        //DBより設定情報を取得。なければデフォルト値とする。
        SchCommonBiz biz = new SchCommonBiz(reqMdl__);
        SchPriConfModel pconf = biz.getSchPriConfModel(con, umodel.getUsrsid());

        //開始時刻
        UDate fromUd = new UDate();
        fromUd.setHour(paramMdl.getSch091DefFrH());
        fromUd.setMinute(paramMdl.getSch091DefFrM());
        //終了時刻
        UDate toUd = new UDate();
        toUd.setHour(paramMdl.getSch091DefToH());
        toUd.setMinute(paramMdl.getSch091DefToM());

        pconf.setSccIniFrDate(fromUd);
        pconf.setSccIniToDate(toUd);
        pconf.setSccEuid(umodel.getUsrsid());
        pconf.setSccEdate(new UDate());
        pconf.setSccIniPublic(paramMdl.getSch091PubFlg());
        pconf.setSccIniEdit(paramMdl.getSch091Edit());
        pconf.setSccIniFcolor(Integer.parseInt(paramMdl.getSch091Fcolor()));
        pconf.setSccIniSame(paramMdl.getSch091Same());

        boolean commitFlg = false;
        try {
            SchPriConfDao dao = new SchPriConfDao(con);
            int count = dao.updateInitData(pconf);
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
}
