package jp.groupsession.v2.ntp.ntp060;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;

import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 案件検索画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp060Biz {
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /** 価格検索条件 以上 */
    public static final int PRICE_MORE = 0;
    /** 価格検索条件 以下 */
    public static final int PRICE_LESS = 1;
    /** 状態初期値 */
    public static final int STATE_ALL = -1;

    /**
     * <br>[機  能] デフォルトコンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Ntp060Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }

    /**
     * <br>[機  能] 初期表示を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp060ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void setInitData(
            Ntp060ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        paramMdl.setDspMod(GSConstNippou.DSP_MOD_ANKEN);

        __setDspData(paramMdl, con);
    }
    /**
     * <br>[機  能] 検索処理を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp060ParamModel
     * @param sessionUserSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    public void doSearch(
            Ntp060ParamModel paramMdl,
            int sessionUserSid,
            Connection con) throws SQLException {

        //検索モデルの設定
        Ntp060SearchModel searchModel = setAnkenSearchModel(paramMdl);

        int maxCnt = 10;
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, sessionUserSid);
        if (pconf != null) {
            maxCnt = pconf.getNprDspList();
        }

        //最大件数
        Ntp060AnkenDao ankenDao = new Ntp060AnkenDao(con);
        int searchCnt = ankenDao.getAnkenCount(searchModel);

        //ページ調整
        int maxPage = searchCnt / maxCnt;
        if ((searchCnt % maxCnt) > 0) {
            maxPage++;
        }
        int page = paramMdl.getNtp060PageTop();
        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }
        paramMdl.setNtp060PageTop(page);
        paramMdl.setNtp060PageBottom(page);
        paramMdl.setNtp060Page((page - 1) * maxCnt);

        //ページコンボ設定
        paramMdl.setNtp060PageCmbList(PageUtil.createPageOptions(searchCnt, maxCnt));

        //案件一覧の取得・設定
        paramMdl.setNtp060AnkenList((ArrayList<Ntp060AnkenModel>)
                ankenDao.select(searchModel, page, maxCnt, reqMdl__));
    }

    /**
     * フォーム情報から検索モデルを生成します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @return Ntp060SearchModel 検索条件モデル
     */
    public Ntp060SearchModel setAnkenSearchModel(Ntp060ParamModel paramMdl) {
        //検索モデルの設定
        Ntp060SearchModel searchModel = new Ntp060SearchModel();

        String mitumori = NullDefault.getStringZeroLength(
                paramMdl.getNtp060NanKinMitumori(), "-1");
        String jutyu = NullDefault.getStringZeroLength(
                paramMdl.getNtp060NanKinJutyu(), "-1");

        searchModel.setNanCode(paramMdl.getNtp060NanCode());
        searchModel.setNanName(paramMdl.getNtp060NanName());
        searchModel.setNtp060AcoCode(paramMdl.getNtp060AcoCode());
        searchModel.setNtp060AcoName(paramMdl.getNtp060AcoName());
        searchModel.setNtp060AcoNameKana(paramMdl.getNtp060AcoNameKana());
        searchModel.setNtp060AbaName(paramMdl.getNtp060AbaName());
        searchModel.setNtp060ShohinName(paramMdl.getNtp060ShohinName());
        searchModel.setNtp060ShohinCategory(paramMdl.getNtp060CatSid());
        searchModel.setNgpSid(paramMdl.getNtp060NgpSid());
        searchModel.setNtp060Mikomi(paramMdl.getNtp060Mikomi());

        searchModel.setNhnKinMitumoriKbn(paramMdl.getNtp060NanKinMitumoriKbn());
        searchModel.setNanKinMitumori(Integer.parseInt(mitumori.replaceAll(",", "")));
        searchModel.setNhnKinJutyuKbn(paramMdl.getNtp060NanKinJutyuKbn());
        searchModel.setNanKinJutyu(Integer.parseInt(jutyu.replaceAll(",", "")));

        searchModel.setNtp060Syodan(paramMdl.getNtp060Syodan());
        searchModel.setNcnSid(paramMdl.getNtp060NcnSid());
        searchModel.setNtp060State(paramMdl.getNtp060State());

        UDate fromDate = null;
        if (!StringUtil.isNullZeroStringSpace(paramMdl.getNtp060FrYear())
                && !StringUtil.isNullZeroStringSpace(paramMdl.getNtp060FrMonth())
                && !StringUtil.isNullZeroStringSpace(paramMdl.getNtp060FrDay())) {
            fromDate = new UDate();
            fromDate.setZeroHhMmSs();
            fromDate.setYear(Integer.parseInt(paramMdl.getNtp060FrYear()));
            fromDate.setMonth(Integer.parseInt(paramMdl.getNtp060FrMonth()));
            fromDate.setDay(Integer.parseInt(paramMdl.getNtp060FrDay()));
        }

        UDate toDate = null;
        if (!StringUtil.isNullZeroStringSpace(paramMdl.getNtp060ToYear())
                && !StringUtil.isNullZeroStringSpace(paramMdl.getNtp060ToMonth())
                && !StringUtil.isNullZeroStringSpace(paramMdl.getNtp060ToDay())) {
            toDate = new UDate();
            toDate.setMaxHhMmSs();
            toDate.setYear(Integer.parseInt(paramMdl.getNtp060ToYear()));
            toDate.setMonth(Integer.parseInt(paramMdl.getNtp060ToMonth()));
            toDate.setDay(Integer.parseInt(paramMdl.getNtp060ToDay()));
        }


        searchModel.setNtp060FrDate(fromDate);
        searchModel.setNtp060ToDate(toDate);
        searchModel.setSortKey1(paramMdl.getNtp060SortKey1());
        searchModel.setSortKey2(paramMdl.getNtp060SortKey2());
        searchModel.setOrderKey1(paramMdl.getNtp060OrderKey1());
        searchModel.setOrderKey2(paramMdl.getNtp060OrderKey2());

        return searchModel;
    }

    /**
     * <br>[機  能] 画面表示データの設定を行う
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp060ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行エラー
     */
    private void __setDspData(Ntp060ParamModel paramMdl, Connection con) throws SQLException {
        NtpCommonBiz cBiz = new NtpCommonBiz(con__, reqMdl__);
        //業務リスト取得
        paramMdl.setNtp060GyomuList(cBiz.getGyomuList(con, ""));
        String mes = "";
        if (paramMdl.getNtp060NgySid() != -1) {
            mes = "選択してください";
        }

        UDate now = new UDate();

        //プロセスリスト取得
        paramMdl.setNtp060ProcessList(cBiz.getProcessList(con, mes, paramMdl.getNtp060NgySid()));
        //コンタクトリスト取得
        paramMdl.setNtp060ContactList(cBiz.getContactList(con, "指定なし"));
        //状態リスト取得
        paramMdl.setNtp060StateList(cBiz.getStateList());
        //ソートキーリスト取得
        paramMdl.setNtp060SortList(__getSortList());
        //年リスト取得
        paramMdl.setNtp060YearList(cBiz.getYearLavel(now.getYear()));
        //月リスト取得
        paramMdl.setNtp060MonthList(cBiz.getMonthLavel());
        //日リスト取得
        paramMdl.setNtp060DayList(cBiz.getDayLavel());
        //カテゴリリスト取得
        paramMdl.setNtp060CategoryList(cBiz.getCategoryLavel());

    }
    /**
     * <br>[機  能] ソートキーリストを取得
     * <br>[解  説]
     * <br>[備  考]
     * @return ソートキーリストリスト
     */
    private List<LabelValueBean> __getSortList() {

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        //labelList.add(new LabelValueBean("", String.valueOf(-1)));

        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(GSConstNippou.SORT_KEY_NAN_SEARCH_ALL_TEXT[i],
                        String.valueOf(GSConstNippou.SORT_KEY_NAN_SEARCH_ALL[i])));
        }
        return labelList;
    }
}
