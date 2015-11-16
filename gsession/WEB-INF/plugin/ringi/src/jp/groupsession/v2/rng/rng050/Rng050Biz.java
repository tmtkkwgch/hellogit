package jp.groupsession.v2.rng.rng050;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.rng.RngConst;
import jp.groupsession.v2.rng.biz.RngBiz;
import jp.groupsession.v2.rng.dao.RingiDao;
import jp.groupsession.v2.rng.model.RingiChannelDataModel;
import jp.groupsession.v2.rng.model.RingiDataModel;
import jp.groupsession.v2.rng.model.RingiSearchModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 稟議 管理者設定 申請中案件管理画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Rng050Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Rng050Biz.class);
    /** Connection */
    private Connection con__ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param reqMdl リクエスト情報
     */
    public Rng050Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示処理を行います
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @param userSid ユーザSID
     * @throws SQLException SQL実行時例外
     */
    public void initDsp(Rng050ParamModel paramMdl, int userSid) throws SQLException {

        log__.debug("start");

        //グループ、ユーザコンボの設定
        GroupBiz gBiz = new GroupBiz();
        UserBiz uBiz = new UserBiz();
        GsMessage gsMsg = new GsMessage(reqMdl__);
        paramMdl.setRng050groupList(gBiz.getGroupCombLabelList(con__, true, gsMsg));
        paramMdl.setRng050userList(
                uBiz.getNormalUserLabelList(con__, paramMdl.getSltGroupSid(), null, true, gsMsg));

        //年、月、日コンボの設定
        paramMdl.setRng050YearList(getYearList(new UDate().getYear()));
        paramMdl.setRng050MonthList(getMonthList());
        paramMdl.setRng050DayList(getDayList());

        if (paramMdl.getRngAdminSearchFlg() == 1) {

            RingiDao ringiDao = new RingiDao(con__);
            //最大件数
            int ringiCnt = ringiDao.getSinseiRingiDataCount(__createSearchModel(paramMdl, 0));

            //１ページの最大表示件数
            RngBiz rngBiz = new RngBiz(con__);
            int viewCount = rngBiz.getViewCount(con__, userSid);

            //ページ調整
            int maxPage = ringiCnt / viewCount;
            if ((ringiCnt % viewCount) > 0) {
                maxPage++;
            }
            int page = paramMdl.getRngAdminPageTop();
            if (page < 1) {
                page = 1;
            } else if (page > maxPage) {
                page = maxPage;
            }
            paramMdl.setRngAdminPageTop(page);
            paramMdl.setRngAdminPageBottom(page);

            //ページコンボ設定
            if (ringiCnt > viewCount) {
                paramMdl.setPageList(PageUtil.createPageOptions(ringiCnt, viewCount));
            }

            //検索結果一覧設定
            List<RingiDataModel> rngDataList
                            = ringiDao.getSinseiRingiDataList(
                                    __createSearchModel(paramMdl, viewCount));

            for (RingiDataModel rngData : rngDataList) {
                List<RingiChannelDataModel> channelList
                        = ringiDao.getChannelList(rngData.getRngSid());
                List<RingiChannelDataModel> apprUser = new ArrayList<RingiChannelDataModel>();
                for (RingiChannelDataModel channelMdl : channelList) {
                    if (channelMdl.getRncType() == RngConst.RNG_RNCTYPE_APPR) {
                        apprUser.add(channelMdl);
                    }
                }

                rngData.setChannelList(apprUser);
            }

            paramMdl.setRng050rngDataList(rngDataList);
        } else {
            //ページ調整
            paramMdl.setRngAdminPageTop(1);
            paramMdl.setRngAdminPageBottom(1);
        }

        log__.debug("end");
    }

    /**
     * <br>[機  能] 検索結果の件数を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータ情報
     * @return 検索結果の件数
     * @throws SQLException SQL実行時例外
     */
    public int getSearchCount(Rng050ParamModel paramMdl) throws SQLException {

        RingiDao ringiDao = new RingiDao(con__);
        return ringiDao.getSinseiRingiDataCount(__createSearchModel(paramMdl, 0));
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
                            gsMsg.getMessage("cmn.year", new String[] {String.valueOf(i)}),
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
        String msg = gsMsg.getMessage("cmn.month");
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month + msg, String.valueOf(month)));
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
        String msg = gsMsg.getMessage("cmn.day");
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + msg, String.valueOf(day)));
            day++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 検索条件Modelを生成する
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl パラメータ情報
     * @param viewCount 最大表示件数
     * @return 検索条件
     */
    private RingiSearchModel __createSearchModel(Rng050ParamModel paramMdl, int viewCount) {
        RingiSearchModel searchModel = new RingiSearchModel();
        searchModel.setTitleSearchFlg(true);
        searchModel.setContentSearchFlg(false);

        if (!StringUtil.isNullZeroString(paramMdl.getRngAdminKeyword())) {
            List<String> title = new ArrayList<String>();
            title.add(paramMdl.getRngAdminKeyword());
            searchModel.setKeyword(title);
        }

        searchModel.setGroupSid(paramMdl.getRngAdminGroupSid());
        searchModel.setUserSid(paramMdl.getRngAdminUserSid());

        searchModel.setApplDateFr(createUDate(paramMdl.getRngAdminApplYearFr(),
                                            paramMdl.getRngAdminApplMonthFr(),
                                            paramMdl.getRngAdminApplDayFr()));
        searchModel.setApplDateTo(createUDate(paramMdl.getRngAdminApplYearTo(),
                                            paramMdl.getRngAdminApplMonthTo(),
                                            paramMdl.getRngAdminApplDayTo()));
        searchModel.setLastMagageDateFr(createUDate(paramMdl.getRngAdminLastManageYearFr(),
                                                    paramMdl.getRngAdminLastManageMonthFr(),
                                                    paramMdl.getRngAdminLastManageDayFr()));
        searchModel.setLastMagageDateTo(createUDate(paramMdl.getRngAdminLastManageYearTo(),
                                                    paramMdl.getRngAdminLastManageMonthTo(),
                                                    paramMdl.getRngAdminLastManageDayTo()));

        searchModel.setSortKey(paramMdl.getRngAdminSortKey());
        searchModel.setOrderKey(paramMdl.getRngAdminOrderKey());
        searchModel.setSortKey2(RngConst.RNG_SORT_DATE);
        searchModel.setOrderKey2(RngConst.RNG_ORDER_DESC);

        searchModel.setPage(paramMdl.getRngAdminPageTop());
        searchModel.setMaxCnt(viewCount);
        searchModel.setAdminFlg(true);

        return searchModel;
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
