package jp.groupsession.v2.ntp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NippouSearchDao;
import jp.groupsession.v2.ntp.model.NtpAdmConfModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;

import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.SchAppendSchData;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.SchAppendDataModel;
import jp.groupsession.v2.cmn.model.SchAppendDataParam;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <br>[機  能] 日報の次のアクションをスケジュールのデータとして返すクラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class NtpSchAppendSchData implements SchAppendSchData {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(NtpSchAppendSchData.class);

    /**
     * <br>[機  能] スケジュールに表示する情報を取得する。
     * <br>[解  説]
     * <br>
     * <br>[備  考]
     * @param paramMdl パラメータ
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @return メッセージのリスト
     * @throws SQLException SQL実行例外
     */
    public List<SchAppendDataModel> getAppendSchData(
            SchAppendDataParam paramMdl, RequestModel reqMdl,
            Connection con) throws SQLException {

        List<SchAppendDataModel> appList = new ArrayList<SchAppendDataModel>();

        //個人設定を取得
        NtpPriConfModel priMdl = new NtpPriConfModel();
        NtpCommonBiz biz = new NtpCommonBiz(con, reqMdl);
        priMdl = biz.getNtpPriConfModel(con, reqMdl.getSmodel().getUsrsid());



        int ntpSch = 0;
        if (priMdl != null) {
            ntpSch = priMdl.getNprSchKbn();
        }

        if (ntpSch == 0) {
            //次のアクションをスケジュールに表示する
            appList = __getSearchModel(con, reqMdl, paramMdl);
        }

        return appList;
    }

    /**
     * <br>[機  能] 次のアクション情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ
     * @param reqMdl リクエストモデル
     * @param con DBコネクション
     * @return ArrayList<SchAppendDataModel>
     * @throws SQLException SQL実行例外
     */
    private ArrayList<SchAppendDataModel> __getSearchModel(Connection con,
                                                RequestModel reqMdl,
                                                SchAppendDataParam paramMdl)
                                                throws SQLException {

        ArrayList<SchAppendDataModel> sadmList = new ArrayList<SchAppendDataModel>();
        log__.debug("START_NtpSchAppendSchData");

        BaseUserModel buMdl = reqMdl.getSmodel();


        //日報の共有範囲を取得
        boolean getFlg = true;
        NtpCommonBiz biz = new NtpCommonBiz(con, reqMdl);
        NtpAdmConfModel conf = biz.getAdmConfModel(con);
        if (conf.getNacCrange() == GSConstNippou.CRANGE_SHARE_GROUP) {
            //日報の共有範囲がグループのみの場合はセッションユーザと表示ユーザが同じグループに所属しているか判定
            GroupDao gDao = new GroupDao(con);
            getFlg = gDao.isSameGroupUser(buMdl.getUsrsid(), paramMdl.getUsrSid());
        }

        if (getFlg) {
            UDate frDate = null;
            UDate toDate = null;
            frDate = paramMdl.getFrDate();
            toDate = paramMdl.getToDate();

            frDate.setZeroHhMmSs();
            toDate.setMaxHhMmSs();

            NippouSearchDao ntpDao = new NippouSearchDao(con);
            List<NtpDataModel> ntpList =
                          ntpDao.selectNextAction(paramMdl.getUsrSid(), frDate, toDate);

            if (!ntpList.isEmpty()) {
                SchAppendDataModel sadm = null;
                for (NtpDataModel ntpMdl : ntpList) {
                    UDate nFrDate = ntpMdl.getNipActionDate();
                    UDate nToDate = ntpMdl.getNipActionDate();

                    sadm = new SchAppendDataModel();
                    sadm.setSchPlgUrl(
                      createActionUrl(reqMdl, paramMdl, ntpMdl.getNipSid(), ntpMdl.getUsrSid()));
                    sadm.setSchPlgId(GSConstNippou.PLUGIN_ID_NIPPOU);
                    sadm.setFromDate(nFrDate);
                    sadm.setToDate(nToDate);
                    sadm.setTimeKbn(1);
                    sadm.setTitle(ntpMdl.getNipTitle());
                    sadm.setValueStr(ntpMdl.getNipAction());
                    sadm.setReturnUrl(paramMdl.getReturnUrl());
                    sadm.setUsrSid(paramMdl.getUsrSid());
                    sadmList.add(sadm);
                }
            }
        }

        log__.debug("END_NtpSchAppendSchData");

        return sadmList;
    }
    /**
     * <br>[機  能] 次のアクション参照画面URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl リクエストモデル
     * @param paramMdl パラメータモデル
     * @param ntpSid 日報ID
     * @param usrSid ユーザID
     * @return スレッドURL
     */
    public String createActionUrl(RequestModel reqMdl,
                                SchAppendDataParam paramMdl,
                                int ntpSid,
                                int usrSid) {

        String actionUrl = null;

        //次のアクション参照画面のURLを作成
//        StringBuffer url = req.getRequestURL();
//        if (url != null || url.toString().length() > 0) {
        String url = reqMdl.getReferer();
        if (!StringUtil.isNullZeroString(url)) {
            actionUrl = url.substring(0, url.lastIndexOf("/"));
            actionUrl = actionUrl.substring(0, actionUrl.lastIndexOf("/"));
            actionUrl += "/nippou/ntp040.do?";
            actionUrl += "CMD=edit";
            actionUrl += "&cmd=edit";
            actionUrl += "&ntp010SelectUsrSid=" + usrSid;
            actionUrl += "&ntp010NipSid=" + ntpSid;
            actionUrl += "&ntp040schUrl=" + paramMdl.getReturnUrl();
        }

        return actionUrl;
    }
}