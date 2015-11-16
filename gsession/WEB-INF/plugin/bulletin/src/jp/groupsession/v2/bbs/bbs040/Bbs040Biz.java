package jp.groupsession.v2.bbs.bbs040;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.bbs.dao.BulletinDao;
import jp.groupsession.v2.bbs.model.BbsForInfModel;
import jp.groupsession.v2.bbs.model.BulletinSearchModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 掲示板 詳細検索画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Bbs040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Bbs040Biz.class);

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @param reqMdl リクエスト情報
     * @throws Exception 実行例外
     */
    public void setInitData(Bbs040ParamModel paramMdl, Connection con, int userSid,
                            boolean admin, RequestModel reqMdl)
    throws Exception {
        log__.debug("START");

        //フォーラムコンボを設定
        BulletinDao bbsDao = new BulletinDao(con);
        List < BbsForInfModel > bbsForList = bbsDao.getForumList(userSid, false);

        List < LabelValueBean > forumLabelList = new ArrayList < LabelValueBean >();
        for (BbsForInfModel bbsForMdl : bbsForList) {
            LabelValueBean bbsForLabel = new LabelValueBean(bbsForMdl.getBfiName(),
                                                        String.valueOf(bbsForMdl.getBfiSid()));
            forumLabelList.add(bbsForLabel);
        }
        paramMdl.setBbs040forumList(forumLabelList);


        //年月日コンボを設定
        int sysYear = (new UDate()).getYear();
        UDate oldWriteDate = bbsDao.getOldestWriteDate(userSid, false);
        int writeOldYear = sysYear;
        if (oldWriteDate != null) {
            writeOldYear = oldWriteDate.getYear();
        }

        paramMdl.setBbs040yearList(__getYearLabelList(reqMdl, writeOldYear, sysYear)); //年
        paramMdl.setBbs040monthList(__getMonthLabelList(reqMdl));                //月
        paramMdl.setBbs040dayList(__getDayLabelList(reqMdl));                  //日


        log__.debug("End");
    }

    /**
     * <br>[機  能] 検索結果件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param con コネクション
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @param now 現在日時
     * @return 検索結果件数
     * @throws Exception 実行例外
     */
    public int getSearchCount(Bbs040ParamModel paramMdl, Connection con, int userSid,
                            boolean admin, UDate now)
    throws Exception {

        //検索条件を設定
        BulletinSearchModel searchMdl = createSearchModel(paramMdl, userSid, admin, now);

        BulletinDao bbsDao = new BulletinDao(con);
        return bbsDao.getSearchDtlCnt(searchMdl);
    }

    /**
     * <br>[機  能] 検索条件を作成する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param admin 管理者か否か true:管理者, false:一般ユーザ
     * @param now 現在日時
     * @return 検索条件
     */
    public BulletinSearchModel createSearchModel(Bbs040ParamModel paramMdl, int userSid,
                                                boolean admin, UDate now) {

        //検索条件を設定
        BulletinSearchModel searchMdl = new BulletinSearchModel();
        searchMdl.setBfiSid(paramMdl.getBbs040forumSid());
        searchMdl.setUserSid(userSid);
        searchMdl.setAdmin(admin);
        searchMdl.setNow(now);

        searchMdl.setKeyword(paramMdl.getS_key());
        searchMdl.setKeywordKbn(paramMdl.getBbs040keyKbn());
        searchMdl.setSearchThreTitleFlg(paramMdl.getBbs040taisyouThread() == 1);
        searchMdl.setSearchWriteValueFlg(paramMdl.getBbs040taisyouNaiyou() == 1);
        searchMdl.setContributorName(paramMdl.getBbs040userName());
        searchMdl.setReadKbn(paramMdl.getBbs040readKbn());
        searchMdl.setWriteDateFlg(paramMdl.getBbs040dateNoKbn() != 1);
        if (searchMdl.isWriteDateFlg()) {
            UDate fromDate = new UDate();
            fromDate.setTime(0);
            fromDate.setYear(paramMdl.getBbs040fromYear());
            fromDate.setMonth(paramMdl.getBbs040fromMonth());
            fromDate.setDay(paramMdl.getBbs040fromDay());
            fromDate.setHour(0);
            fromDate.setMinute(0);
            fromDate.setSecond(0);
            fromDate.setMilliSecond(0);
            searchMdl.setWriteDateFrom(fromDate);

            UDate toDate = new UDate();
            toDate.setTime(0);
            toDate.setYear(paramMdl.getBbs040toYear());
            toDate.setMonth(paramMdl.getBbs040toMonth());
            toDate.setDay(paramMdl.getBbs040toDay());
            toDate.setHour(23);
            toDate.setMinute(59);
            toDate.setSecond(59);
            toDate.setMilliSecond(999);
            searchMdl.setWriteDateTo(toDate);
        }

        return searchMdl;
    }

    /**
     * <p>年コンボを作成する
     * @param reqMdl リクエスト情報
     * @param start 開始
     * @param end 終了
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getYearLabelList(RequestModel reqMdl, int start, int end) {

        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                       gsMsg.getMessage("cmn.year", new String[] {String.valueOf(value)}),
                       String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * <p>月コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getMonthLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 12;
        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                            value + gsMsg.getMessage("cmn.month"), String.valueOf(value)));
        }
        return labelList;
    }
    /**
     * <p>日コンボを作成する
     * @param reqMdl リクエスト情報
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getDayLabelList(RequestModel reqMdl) {

        int start = 1;
        int end = 31;

        GsMessage gsMsg = new GsMessage(reqMdl);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                             value + gsMsg.getMessage("cmn.day"), String.valueOf(value)));
        }
        return labelList;
    }
}
