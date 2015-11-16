package jp.groupsession.v2.rng.rng130;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RingiSearchModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議詳細検索画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng130Biz {

    /** Loggingインスタンス */
    private static Log log__ = LogFactory.getLog(Rng130Biz.class);

    /** コネクション */
    private Connection con__ = null;

    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     */
    public Rng130Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rng130Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説] 処理モード = 編集の場合、稟議情報を設定する
     * <br>[備  考]
     * @param reqMdl リクエスト情報
     * @param paramMdl パラメータ情報
     * @param appRoot アプリケーションのルートパス
     * @param tempDir テンポラリディレクトリパス
     * @param userMdl セッションユーザ情報
     * @throws SQLException SQL実行例外
     * @throws IOException 添付ファイルの操作に失敗
     * @throws IOToolsException 添付ファイルの操作に失敗
     */
    public void setInitData(RequestModel reqMdl, Rng130ParamModel paramMdl,
                            String appRoot, String tempDir, BaseUserModel userMdl)
    throws IOException, IOToolsException, SQLException {

        int userSid = userMdl.getUsrsid();

        //年、月、日コンボの設定
        paramMdl.setRng130YearList(getYearList(new UDate().getYear()));
        paramMdl.setRng130MonthList(getMonthList());
        paramMdl.setRng130DayList(getDayList());

        //ソートキー設定
        paramMdl.setSortKeyList(createSortKeyList(paramMdl.getRng130Type()));

        if (paramMdl.getRng130searchFlg() == 1) {

            RingiDao ringiDao = new RingiDao(con__);
            int type = paramMdl.getSvRng130Type();

            //最大件数
            int ringiCnt = ringiDao.getRingiDataCount(
                    __createSearchModel(paramMdl, userSid, 0, false), type);

            //１ページの最大表示件数
            RngBiz rngBiz = new RngBiz(con__);
            int viewCount = rngBiz.getViewCount(con__, userSid);

            //ページ調整
            int maxPage = ringiCnt / viewCount;
            if ((ringiCnt % viewCount) > 0) {
                maxPage++;
            }
            int page = paramMdl.getRng130pageTop();
            if (page < 1) {
                page = 1;
            } else if (page > maxPage) {
                page = maxPage;
            }
            paramMdl.setRng130pageTop(page);
            paramMdl.setRng130pageBottom(page);

            //ページコンボ設定
            if (ringiCnt > viewCount) {
                paramMdl.setPageList(PageUtil.createPageOptions(ringiCnt, viewCount));
            }

            //検索結果一覧設定
            List<RingiDataModel> rngDataList
                            = ringiDao.getRingiDataList(
                                        __createSearchModel(paramMdl, userSid, viewCount, false),
                                        type);

            paramMdl.setRng130rngDataList(rngDataList);
        } else {
            //ページ調整
            paramMdl.setRng130pageTop(1);
            paramMdl.setRng130pageBottom(1);
        }

        log__.debug("End");
    }

    /**
     * <br>[機  能] 検索結果の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid セッションユーザSID
     * @return 検索結果の件数
     * @throws SQLException SQL実行時例外
     */
    public int getSearchCount(Rng130ParamModel paramMdl, int userSid) throws SQLException {

        RingiDao ringiDao = new RingiDao(con__);
        return ringiDao.getRingiDataCount(__createSearchModel(paramMdl, userSid, 0, true),
                                        paramMdl.getRng130Type());
    }

    /**
     * <br>[機  能] ソートキーの一覧を作成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param type 種別
     * @return ソートキーの一覧
     */
    public List<LabelValueBean> createSortKeyList(int type) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String title = gsMsg.getMessage("cmn.title");
        String user = gsMsg.getMessage("rng.47");
        String shiDate = gsMsg.getMessage("rng.application.date");
        String recDate = gsMsg.getMessage("cmn.received.date");
        String lastDate = gsMsg.getMessage("rng.105");
        String creDate = gsMsg.getMessage("rng.37");

        List<LabelValueBean> sortKeyList = new ArrayList<LabelValueBean>();
        sortKeyList.add(new LabelValueBean(title, String.valueOf(RngConst.RNG_SORT_TITLE)));
        if (type != RngConst.RNG_MODE_SOUKOU) {
            sortKeyList.add(new LabelValueBean(user, String.valueOf(RngConst.RNG_SORT_NAME)));
            sortKeyList.add(new LabelValueBean(shiDate, String.valueOf(RngConst.RNG_SORT_DATE)));
        }
        if (type == RngConst.RNG_MODE_JYUSIN) {
            sortKeyList.add(new LabelValueBean(recDate, String.valueOf(RngConst.RNG_SORT_JYUSIN)));
        }
        if (type == RngConst.RNG_MODE_SINSEI || type == RngConst.RNG_MODE_KANRYO) {
            sortKeyList.add(new LabelValueBean(lastDate,
                                            String.valueOf(RngConst.RNG_SORT_KAKUNIN)));
        }
        if (type == RngConst.RNG_MODE_SOUKOU) {
            sortKeyList.add(new LabelValueBean(creDate, String.valueOf(RngConst.RNG_SORT_TOUROKU)));
        }

        return sortKeyList;
    }

    /**
     * <br>[機  能] 検索条件Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @param viewCount 最大表示件数
     * @param countFlg 最大件数フラグ
     * @return 検索条件
     */
    private RingiSearchModel __createSearchModel(Rng130ParamModel paramMdl, int userSid,
                                                int viewCount, boolean countFlg) {
        RingiSearchModel searchModel = new RingiSearchModel();

        if (countFlg) {
            searchModel.setKeyword(RngBiz.createKeywordList(paramMdl.getRngKeyword()));
            searchModel.setKeywordType(paramMdl.getRng130keyKbn());
            searchModel.setTitleSearchFlg(paramMdl.getRng130searchSubject1() == 1);
            searchModel.setContentSearchFlg(paramMdl.getRng130searchSubject2() == 1);

            searchModel.setApplDateFr(createUDate(paramMdl.getSltApplYearFr(),
                                        paramMdl.getSltApplMonthFr(),
                                        paramMdl.getSltApplDayFr()));
            searchModel.setApplDateTo(createUDate(paramMdl.getSltApplYearTo(),
                                        paramMdl.getSltApplMonthTo(),
                                        paramMdl.getSltApplDayTo()));
            searchModel.setLastMagageDateFr(createUDate(paramMdl.getSltLastManageYearFr(),
                                        paramMdl.getSltLastManageMonthFr(),
                                        paramMdl.getSltLastManageDayFr()));
            searchModel.setLastMagageDateTo(createUDate(paramMdl.getSltLastManageYearTo(),
                                        paramMdl.getSltLastManageMonthTo(),
                                        paramMdl.getSltLastManageDayTo()));

        } else {
            searchModel.setKeyword(RngBiz.createKeywordList(paramMdl.getSvRngKeyword()));
            searchModel.setKeywordType(paramMdl.getSvRng130keyKbn());
            searchModel.setTitleSearchFlg(paramMdl.getSvRng130searchSubject1() == 1);
            searchModel.setContentSearchFlg(paramMdl.getSvRng130searchSubject2() == 1);

            searchModel.setApplDateFr(createUDate(paramMdl.getSvApplYearFr(),
                                        paramMdl.getSvApplMonthFr(),
                                        paramMdl.getSvApplDayFr()));
            searchModel.setApplDateTo(createUDate(paramMdl.getSvApplYearTo(),
                                        paramMdl.getSvApplMonthTo(),
                                        paramMdl.getSvApplDayTo()));
            searchModel.setLastMagageDateFr(createUDate(paramMdl.getSvLastManageYearFr(),
                                        paramMdl.getSvLastManageMonthFr(),
                                        paramMdl.getSvLastManageDayFr()));
            searchModel.setLastMagageDateTo(createUDate(paramMdl.getSvLastManageYearTo(),
                                        paramMdl.getSvLastManageMonthTo(),
                                        paramMdl.getSvLastManageDayTo()));
        }

        searchModel.setUserSid(userSid);
        searchModel.setSortKey(paramMdl.getSvSortKey1());
        searchModel.setOrderKey(paramMdl.getSvRng130orderKey1());
        searchModel.setSortKey2(paramMdl.getSvSortKey2());
        searchModel.setOrderKey2(paramMdl.getSvRng130orderKey2());
        searchModel.setPage(paramMdl.getRng130pageTop());
        searchModel.setMaxCnt(viewCount);
        searchModel.setAdminFlg(false);

        return searchModel;
    }

    /**
     * <br>表示開始日から年コンボを生成します
     * @param year 基準年
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public ArrayList<LabelValueBean> getYearList(int year) {
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("　", "-1"));
        GsMessage gsMsg = new GsMessage(reqMdl__);
        for (int i = 2000; i <= year; i++) {
            labelList.add(
                    new LabelValueBean(
                            gsMsg.getMessage("cmn.year",
                                    new String[]{String.valueOf(i)}),
                                                String.valueOf(i)));
        }
        return labelList;
    }

    /**
     * <br>月コンボを生成します
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    public ArrayList<LabelValueBean> getMonthList() {
        int month = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("　", "-1"));
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msgMonth = gsMsg.getMessage("cmn.month");
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month + msgMonth, String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>日コンボを生成します
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    public ArrayList<LabelValueBean> getDayList() {
        int day = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        labelList.add(new LabelValueBean("　", "-1"));
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String msgDay = gsMsg.getMessage("cmn.day");
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + msgDay, String.valueOf(day)));
            day++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] UDateのインスタンスを生成します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return UDate
     */
    public UDate createUDate(int year, int month, int day) {
        if (year <= 0 || month <= 0 || day <= 0) {
            return null;
        }

        UDate date = UDate.getInstance(0);
        date.setDate(year, month, day);
        return date;
    }
}
