package jp.groupsession.v2.ntp.ntp100;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.biz.UserBiz;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnMyGroupMsDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBelongmModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NippouSearchDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
import jp.groupsession.v2.ntp.dao.NtpColMsgDao;
import jp.groupsession.v2.ntp.dao.NtpKtbunruiDao;
import jp.groupsession.v2.ntp.dao.NtpKthouhouDao;
import jp.groupsession.v2.ntp.model.NippouListSearchModel;
import jp.groupsession.v2.ntp.model.NtpAnkenModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.model.NtpKtbunruiModel;
import jp.groupsession.v2.ntp.model.NtpKthouhouModel;
import jp.groupsession.v2.ntp.model.NtpLabelValueModel;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;
import jp.groupsession.v2.ntp.ntp010.Ntp010Biz;
import jp.groupsession.v2.ntp.ntp010.Ntp010UsrModel;
import jp.groupsession.v2.ntp.ntp010.SimpleNippouModel;
import jp.groupsession.v2.sch.biz.SchCommonBiz;
import jp.groupsession.v2.struts.msg.GsMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 日報一覧画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp100Biz.class);
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;

    /** 活動分類格納MAP */
    public Map<Integer, String> ktBunruiMap__ = null;
    /** 活動方法格納MAP */
    public Map<Integer, String> ktHouhouMap__ = null;

    /**
     * <p>Set HttpServletRequest
     * @param reqMdl RequestModel
     */
    public Ntp100Biz(RequestModel reqMdl) {
        reqMdl__ = reqMdl;
    }

    /**
     * 初期表示画面情報を取得します
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @return Ntp100Form アクションフォーム
     * @throws SQLException SQL実行時例外
     */
    public Ntp100ParamModel getInitData(Ntp100ParamModel paramMdl,
                             Connection con) throws SQLException {

        paramMdl.setListMod("");

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        if (StringUtil.isNullZeroStringSpace(paramMdl.getNtp010SelectUsrSid())) {
            paramMdl.setNtp010SelectUsrSid(String.valueOf(sessionUsrSid));
        }

        //セッション情報からユーザモデル生成
        Ntp010UsrModel uMdl = new Ntp010UsrModel();
        uMdl.setUsrSid(sessionUsrSid);
        uMdl.setUsrKbn(GSConstNippou.USER_KBN_USER);
        paramMdl.setNtp100UsrMdl(uMdl);
        paramMdl.setNtp010SelectUsrSid(NullDefault.getString(paramMdl.getNtp010SelectUsrSid(),
                String.valueOf(sessionUsrSid)));
        //管理者権限
        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(con, usModel,
                                                     GSConstNippou.PLUGIN_ID_NIPPOU);
        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }
        //グループ・ユーザコンボ設定
        __setGroupUserCombo(paramMdl, sessionUsrSid, con);

        //活動分類コンボ設定
        paramMdl.setNtp100KtbunruiLavel(getKtbunruiLabelList(con));

        //活動方法コンボ設定
        paramMdl.setNtp100KthouhouLavel(getKthouhouLabelList(con));

        //案件情報取得
        if (paramMdl.getParamAnkenSid() > 0) {
            //パラメータに案件が設定されている場合は案件SIDを設定
            paramMdl.setNtp100AnkenSid(String.valueOf(paramMdl.getParamAnkenSid()));
            //グループ指定なし
            paramMdl.setNtp100SltGroup(String.valueOf(-1));
            //ユーザ指定なし
            paramMdl.setNtp100SltUser(String.valueOf(-1));
            //開始日を1年前に設定
            UDate frDspDate = new UDate();
            frDspDate.addYear(-1);
            paramMdl.setNtp100SltYearFr(String.valueOf(frDspDate.getYear()));
            paramMdl.setNtp100SltMonthFr(String.valueOf(frDspDate.getMonth()));
            paramMdl.setNtp100SltDayFr(String.valueOf(frDspDate.getIntDay()));
        }
        paramMdl.setNtp100NtpAnkenModel(__getAnken(con, paramMdl.getNtp100AnkenSid()));
        //会社情報
        paramMdl.setNtp100AdrCompanyMdl(__getCompanyData(con, paramMdl.getNtp100CompanySid()));
        //会社拠点情報
        paramMdl.setNtp100AdrCompanyBaseMdl(
                __getCompanyBaseData(con, paramMdl.getNtp100CompanyBaseSid()));

        //開始・終了期間コンボ設定
        __setDateCombo(paramMdl);
        //ソートコンボ設定
        __setSortCombo(paramMdl);
        //カラーコメントリスト設定
        __setColorComment(paramMdl, con);

        return paramMdl;
    }

    /**
     * <br>[機  能] DBから案件情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param nanSid 案件SID
     * @return ankenModel 案件モデル
     * @throws SQLException SQL実行時例外
     */
    public NtpAnkenModel __getAnken(Connection con,
                              String nanSid) throws SQLException {

        int ankenSid = NullDefault.getInt(nanSid, 0);
        NtpAnkenModel ankenModel = null;

        //案件情報
        if (ankenSid > 0) {
            NtpAnkenDao ankenDao = new NtpAnkenDao(con);
            ankenModel = ankenDao.select(ankenSid);
        }

        return ankenModel;
    }

    /**
     * <br>[機  能] DBから会社情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param acoSid 会社SID
     * @return companyModel 会社情報
     * @throws SQLException SQL実行時例外
     */
    public AdrCompanyModel __getCompanyData(Connection con,
                                 String acoSid) throws SQLException {

        int compSid = NullDefault.getInt(acoSid, 0);
        AdrCompanyModel companyModel = null;

        //会社情報
        if (compSid > 0) {
            AdrCompanyDao companyDao = new AdrCompanyDao(con);
            companyModel = companyDao.select(compSid);
        }
        return companyModel;
    }

    /**
     * <br>[機  能] DBから会社拠点情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param abaSid 拠点SID
     * @return companyBaseMdl 会社拠点情報
     * @throws SQLException SQL実行時例外
     */
    public AdrCompanyBaseModel __getCompanyBaseData(Connection con,
                                 String abaSid) throws SQLException {

        int compBaseSid = NullDefault.getInt(abaSid, 0);
        AdrCompanyBaseModel companyBaseMdl = null;

        //会社拠点情報
        if (compBaseSid > 0) {
            AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
            companyBaseMdl = new AdrCompanyBaseModel();
            companyBaseMdl = companyBaseDao.select(compBaseSid);
        }
        return companyBaseMdl;
    }

    /**
     * <br>[機  能] 検索対象がNULLの場合、検索対象のデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     */
    public void setDefultSearchTarget(Ntp100ParamModel paramMdl) {
        //検索対象
        if (paramMdl.getNtp100SearchTarget() == null
                || paramMdl.getNtp100SearchTarget().length == 0) {
            paramMdl.setNtp100SearchTarget(getDefultSearchTarget());
        }
    }
    /**
     * <br>[機  能] 検索対象がNULLの場合、タイトルカラーのデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     */
    public void setDefultBgcolor(Ntp100ParamModel paramMdl) {
        //検索対象
        if (paramMdl.getNtp100Bgcolor() == null
                || paramMdl.getNtp100Bgcolor().length == 0) {
            paramMdl.setNtp100Bgcolor(Ntp100Biz.getDefultBgcolor());
        }
    }

    /**
     * <br>[機  能] 検索対象がNULLの場合、見込み度のデフォルト値を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     */
    public void setDefultMikomido(Ntp100ParamModel paramMdl) {
        //検索対象
        if (paramMdl.getNtp100Mikomido() == null
                || paramMdl.getNtp100Mikomido().length == 0) {
            paramMdl.setNtp100Mikomido(Ntp100Biz.getDefultMikomido());
        }
    }

    /**
     * <br>[機  能] 検索対象のデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトターゲット配列
     */
    public static String[] getDefultSearchTarget() {
        String[] targets = {
                String.valueOf(GSConstNippou.SEARCH_TARGET_TITLE),
                String.valueOf(GSConstNippou.SEARCH_TARGET_HONBUN)
              };
        return targets;
    }

    /**
     * <br>[機  能] タイトルカラーのデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトタイトルカラー配列
     */
    public static String[] getDefultBgcolor() {
        String[] bgcolors = {
                String.valueOf(GSConstNippou.BGCOLOR_BLUE),
                String.valueOf(GSConstNippou.BGCOLOR_RED),
                String.valueOf(GSConstNippou.BGCOLOR_GREEN),
                String.valueOf(GSConstNippou.BGCOLOR_ORANGE),
                String.valueOf(GSConstNippou.BGCOLOR_BLACK)
              };
        return bgcolors;
    }

    /**
     * <br>[機  能] 見込み度のデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルト見込み度配列
     */
    public static String[] getDefultMikomido() {
        String[] mikomido = {
                String.valueOf(0),
                String.valueOf(1),
                String.valueOf(2),
                String.valueOf(3),
                String.valueOf(4)
              };
        return mikomido;
    }

    /**
     * フォーム情報から検索モデルを生成します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl フォーム
     * @param con コネクション
     * @param sessionUsrSid ユーザSID
     * @throws SQLException SQL実行時例外
     * @return NippouListSearchModel 検索条件モデル
     */
    public NippouListSearchModel setNippouListSearchModel(
            Ntp100ParamModel paramMdl, Connection con, int sessionUsrSid) throws SQLException {


        CommonBiz cBiz = new CommonBiz();
        NippouListSearchModel searchMdl = new NippouListSearchModel();
        int userSid = Integer.parseInt(paramMdl.getNtp100SltUser());
//        int userKbn = Integer.parseInt(paramMdl.getNtp010SelectUsrKbn());
        searchMdl.setUsrSid(userSid);
//        searchMdl.setNtpUsrKbn(userKbn);
        searchMdl.setNtpOrder(paramMdl.getNtp100SvOrderKey1());
        searchMdl.setNtpSortKey(paramMdl.getNtp100SvSortKey1());
        searchMdl.setNtpOrder2(paramMdl.getNtp100SvOrderKey2());
        searchMdl.setNtpSortKey2(paramMdl.getNtp100SvSortKey2());
        searchMdl.setKeyWordkbn(Integer.parseInt(paramMdl.getNtp100SvKeyWordkbn()));
        String keyWord = NullDefault.getString(paramMdl.getNtp100SvKeyValue(), "");
        searchMdl.setNtpKeyValue(cBiz.setKeyword(keyWord));

        searchMdl.setKatudouBunrui(NullDefault.getInt(paramMdl.getNtp100SvKtbunrui(), -1));
        searchMdl.setKatudouHouhou(NullDefault.getInt(paramMdl.getNtp100SvKthouhou(), -1));

        searchMdl.setAnkenSid(NullDefault.getInt(paramMdl.getNtp100SvAnkenSid(), -1));
        searchMdl.setCompanySid(NullDefault.getInt(paramMdl.getNtp100SvCompanySid(), -1));
        searchMdl.setCompanyBaseSid(NullDefault.getInt(paramMdl.getNtp100SvCompanyBaseSid(), -1));

        String[] targets = paramMdl.getNtp100SvSearchTarget();
        boolean targetTitle = false;
        boolean targetBody = false;
        if (targets != null && targets.length > 0) {
            for (String target : targets) {
                if (String.valueOf(GSConstNippou.SEARCH_TARGET_TITLE).equals(target)) {
                    targetTitle = true;
                }
                if (String.valueOf(GSConstNippou.SEARCH_TARGET_HONBUN).equals(target)) {
                    targetBody = true;
                }
            }
        }
        searchMdl.setTargetTitle(targetTitle);
        searchMdl.setTargetValue(targetBody);
        //タイトル・内容についての検索条件がある場合は公開スケジュールを対象に検索
//        if (StringUtil.isNullZeroString(paramMdl.getNtp100SvKeyValue())
//                ) {
//            searchMdl.setNtpPublic(-1);
//        } else {
//            searchMdl.setNtpPublic(GSConstNippou.DSP_PUBLIC);
//        }

        //データが存在しない場合、グループが削除されていた場合はデフォルト所属グループを返す
        NtpCommonBiz scBiz = new NtpCommonBiz(con, reqMdl__);
        //デフォルト表示グループ
        String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
        int dfGpSid = NtpCommonBiz.getDspGroupSid(dfGpSidStr);

        int gpSid = 0;
        boolean myGrpFlg = false;

        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getNtp100SvSltGroup(), dfGpSidStr);

        if (NtpCommonBiz.isMyGroupSid(dspGpSidStr)) {
            gpSid = NtpCommonBiz.getDspGroupSid(dspGpSidStr);
            //マイグループSIDをセット
            searchMdl.setNtpSltGroupSid(dspGpSidStr);
            myGrpFlg = true;
        } else {
            gpSid = NullDefault.getInt(paramMdl.getNtp100SvSltGroup(), dfGpSid);
            //通常グループSIDをセット
            searchMdl.setNtpSltGroupSid(String.valueOf(gpSid));
        }
        searchMdl.setMyGrpFlg(myGrpFlg);


//        //ログインユーザが検索グループに所属しているか
//        if (gpBiz.isBelongGroup(sessionUsrSid, gpSid, con)) {
//            searchMdl.setGpBelongKbn(GSConstNippou.EDIT_CONF_GROUP);
//        }

        //検索モデルへ設定
        if (paramMdl.getNtp100SvSltUser() != null
                && paramMdl.getNtp100SvSltUser().length() > 0) {
            searchMdl.setNtpSltUserSid(Integer.parseInt(paramMdl.getNtp100SvSltUser()));
        }

        UDate date1 = new UDate();
        date1.setDate(
                Integer.parseInt(paramMdl.getNtp100SvSltYearFr()),
                Integer.parseInt(paramMdl.getNtp100SvSltMonthFr()),
                Integer.parseInt(paramMdl.getNtp100SvSltDayFr()));
        UDate date2 = new UDate();
        date2.setDate(
                Integer.parseInt(paramMdl.getNtp100SvSltYearTo()),
                Integer.parseInt(paramMdl.getNtp100SvSltMonthTo()),
                Integer.parseInt(paramMdl.getNtp100SvSltDayTo()));
        searchMdl.setNtpDateFrom(date1);
        searchMdl.setNtpDateTo(date2);
        searchMdl.setBgcolor(paramMdl.getNtp100SvBgcolor());
        searchMdl.setMikomido(paramMdl.getNtp100SvMikomido());

        return searchMdl;
    }

    /**
     * 検索結果情報を取得します
     * @param paramMdl アクションフォーム
     * @param con コネクション
     * @return int 検索結果件数
     * @throws SQLException SQL実行時例外
     */
    public int getSearchResult(
                        Ntp100ParamModel paramMdl,
                        Connection con) throws SQLException {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID
        NtpCommonBiz biz = new NtpCommonBiz(con, reqMdl__);
        NtpPriConfModel pconf = biz.getNtpPriConfModel(con, sessionUsrSid);
        //１ページ表示件数
        int limit = pconf.getNprDspList();

        //日報検索情報を取得
        NippouListSearchModel searchMdl = setNippouListSearchModel(paramMdl, con, sessionUsrSid);

        //全データ件数
        int maxCount = getNippouCnt(
                paramMdl,
                searchMdl,
                sessionUsrSid,
                con);

        log__.debug("getNippouCn==>" + maxCount);
        //現在ページ、スタート行
        int nowPage = paramMdl.getNtp100PageNum();
        int offset = PageUtil.getRowNumber(nowPage, limit);
        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < offset) {
            nowPage = maxPageNum;
            offset = maxPageStartRow;
        }
        searchMdl.setNtpOffset(offset);
        searchMdl.setNtpLimit(limit);

        paramMdl.setNtp100NippouList(
                __getNtpList(
                        paramMdl,
                        searchMdl,
                        sessionUsrSid,
                        true,
                        con)
                        );

        //ページング
        paramMdl.setNtp100PageNum(nowPage);
        paramMdl.setNtp100Slt_page1(nowPage);
        paramMdl.setNtp100Slt_page2(nowPage);
        paramMdl.setNtp100PageLabel(
            PageUtil.createPageOptions(maxCount, limit));
        return maxCount;
    }

    /**
     * <br>指定ユーザの月間日報を取得します
     * @param paramMdl アクションフォーム
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @return int データ件数
     * @throws SQLException SQL実行時例外
     */
    public int getNippouCnt(
            Ntp100ParamModel paramMdl,
            NippouListSearchModel searchMdl,
            int sessionUsrSid,
            Connection con) throws SQLException {

        //DB日報件数
        int ret = 0;

//        //ユーザ情報を保持
//        Ntp010UsrModel usMdl = null;
//
//        //指定ユーザ日報
//        usMdl = new Ntp010UsrModel();
//        UserSearchDao usrDao = new UserSearchDao(con);

        //日報情報を取得(指定ユーザ)
        NippouSearchDao ntpDao = new NippouSearchDao(con);
        ret = ntpDao.getCount(searchMdl, sessionUsrSid);
        return ret;
    }

    /**
     * <br>指定ユーザの月間日報を取得します
     * @param paramMdl アクションフォーム
     * @param searchMdl 検索条件
     * @param sessionUsrSid セッションユーザSID
     * @param offset オフセット有無
     * @param con コネクション
     * @return ArrayList グループ>指定ユーザの順に格納
     * @throws SQLException SQL実行時例外
     */
    private ArrayList<SimpleNippouModel> __getNtpList(
            Ntp100ParamModel paramMdl,
            NippouListSearchModel searchMdl,
            int sessionUsrSid,
            boolean offset,
            Connection con) throws SQLException {

        //DB日報情報
        ArrayList < NtpDataModel > ntpDataList = null;
        //指定ユーザ日報
        NippouSearchDao ntpDao = new NippouSearchDao(con);
        //日報情報取得
        ntpDataList = ntpDao.select(searchMdl, sessionUsrSid, offset);
        log__.debug("日報件数==>" + ntpDataList.size());
        UDate now = new UDate();
        UDate dspDate = new UDate();

        if (paramMdl.getNtp010DspDate() != null) {
            dspDate.setDate(paramMdl.getNtp010DspDate());
        } else {
            paramMdl.setNtp010DspDate(UDateUtil.getYYMD(now));
            dspDate.setDate(UDateUtil.getYYMD(now));
        }


        ArrayList<SimpleNippouModel> dayMdlList = new ArrayList<SimpleNippouModel>();
        SimpleNippouModel dspNtpMdl = null;
        NtpDataModel ntpMdl = null;

        //データが存在しない場合、グループが削除されていた場合はデフォルト所属グループを返す
        NtpCommonBiz ntBiz = new NtpCommonBiz(con, reqMdl__);
        //デフォルト表示グループ
        String dfGpSidStr = ntBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
        int dfGpSid = NtpCommonBiz.getDspGroupSid(dfGpSidStr);

        int gpSid = 0;
        //表示グループ
        String dspGpSidStr = NullDefault.getString(paramMdl.getNtp100SltGroup(), dfGpSidStr);
        boolean myGrpHnt = false;
        if (NtpCommonBiz.isMyGroupSid(dspGpSidStr)) {
            gpSid = NtpCommonBiz.getDspGroupSid(dspGpSidStr);
            myGrpHnt = true;
        } else {
            gpSid = NullDefault.getInt(paramMdl.getNtp100SltGroup(), dfGpSid);
        }

        for (int j = 0; j < ntpDataList.size(); j++) {
            ntpMdl = ntpDataList.get(j);
            dspNtpMdl = new SimpleNippouModel();

            //表示日報を設定
            dspNtpMdl = __getNtpDspMdl(ntpMdl, sessionUsrSid, gpSid, con, myGrpHnt);
            dayMdlList.add(dspNtpMdl);
        }
        return dayMdlList;
    }

    /**
     * 日報一覧用の日付文字列を作成する
     * @param ud 変換するUDate
     * @return String 文字列変換されたud
     */
    public static String getDateString(UDate ud) {

        String ret = "";
        if (ud == null) {
            return ret;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(UDateUtil.getSlashYYMD(ud));
        sb.append("　");
        sb.append(UDateUtil.getSeparateHM(ud));
        ret = sb.toString();
        return ret;
    }
    /**
     * <br>[機  能] 指定グループに所属するユーザリストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param groupSid グループSID
     * @param userSid セッションユーザSID
     * @param myGroupFlg マイグループ選択
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getUserLabelList(Connection con,
            int groupSid, int userSid, boolean myGroupFlg) throws SQLException {
        //指定無し
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textWithoutSpecify = gsMsg.getMessage("cmn.without.specifying");
        List < LabelValueBean > labelList = null;
        UserBiz usrBiz = new UserBiz();
        if (myGroupFlg) {
            labelList = usrBiz.getMyGroupUserLabelList(con, userSid, groupSid, null);
        } else {
            labelList = usrBiz.getNormalUserLabelList(con, groupSid, null, false, gsMsg);
        }
        labelList.add(0, new LabelValueBean(textWithoutSpecify, "-1"));
        return labelList;
    }

    /**
     * 検索条件部分のグループ、ユーザコンボを生成する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Sch100Form
     * @param sessionUsrSid セッションユーザSID
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setGroupUserCombo(
            Ntp100ParamModel paramMdl,
            int sessionUsrSid,
            Connection con)
    throws SQLException {

        //検索対象ユーザSID
        if (StringUtil.isNullZeroStringSpace(paramMdl.getNtp100SltUser())) {
            paramMdl.setNtp100SltUser(paramMdl.getNtp010SelectUsrSid());
        }
        int userSid = Integer.parseInt(paramMdl.getNtp100SltUser());

        int userKbn = Integer.parseInt(paramMdl.getNtp010SelectUsrKbn());
        String gpSid = NullDefault.getString(
                paramMdl.getNtp100SltGroup(), paramMdl.getNtp010DspGpSid());
        //グループスケジュール
        if (userSid == -1 && !SchCommonBiz.isMyGroupSid(gpSid)) {
            userSid = NullDefault.getInt(gpSid, 0);
            userKbn = GSConstSchedule.USER_KBN_GROUP;
        } else {
            if (paramMdl.getNtp100SltUser() != null) {
                userKbn = GSConstSchedule.USER_KBN_USER;
            }
        }
        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, reqMdl__);

        //グループコンボのラベルを取得する。
        List<NtpLabelValueModel> groupLabel = getGroupLabelList(con, sessionUsrSid);
        paramMdl.setNtp100GroupLabel(groupLabel);

        SchCommonBiz scBiz = new SchCommonBiz(reqMdl__);
        int dspGpSid = -1;
        int dspUsrSid = -1;
        boolean myGroupFlg = false;
        if (userKbn == GSConstSchedule.USER_KBN_GROUP) {
            dspGpSid = NullDefault.getInt(paramMdl.getNtp100SltGroup(), userSid);
            dspUsrSid = NullDefault.getInt(paramMdl.getNtp100SltUser(), -1);
            paramMdl.setNtp100SltGroup(String.valueOf(dspGpSid));
        } else {
            String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);
            String dspGpSidStr = NullDefault.getString(gpSid, dfGpSidStr);
            dspGpSidStr = ntpBiz.getEnableSelectGroup(groupLabel, dspGpSidStr, dfGpSidStr);
            if (SchCommonBiz.isMyGroupSid(dspGpSidStr)) {
                myGroupFlg = true;
            }
            dspGpSid = SchCommonBiz.getDspGroupSid(dspGpSidStr);
            dspUsrSid = NullDefault.getInt(paramMdl.getNtp100SltUser(), userSid);
            paramMdl.setNtp100SltGroup(dspGpSidStr);
        }

        //表示グループに本人が未所属で本人のスケジュールを表示する場合、
        if (userSid == sessionUsrSid) {
            //デフォルト所属グループ
            GroupBiz gpBiz = new GroupBiz();
            int sysDfGpSid = gpBiz.getDefaultGroupSid(sessionUsrSid, con);
            if (myGroupFlg) {
                CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con);
                String[] users = new String[]{String.valueOf(userSid)};
                if (mgmsDao.getMyGroupMsInfo(sessionUsrSid, dspGpSid, users, true).size() < 1) {
                    dspGpSid = sysDfGpSid;
                    myGroupFlg = false;
                    paramMdl.setNtp100SltGroup(String.valueOf(dspGpSid));
                }
            } else {
                CmnBelongmDao dao = new CmnBelongmDao(con);
                CmnBelongmModel mdl = dao.select(userSid, dspGpSid);
                if (mdl == null) {
                    dspGpSid = sysDfGpSid;
                    paramMdl.setNtp100SltGroup(String.valueOf(dspGpSid));
                }
            }
        }

        //ユーザコンボ
        List<LabelValueBean> userLabel = getUserLabelList(con, dspGpSid, sessionUsrSid, myGroupFlg);
        paramMdl.setNtp100UserLabel(userLabel);

        //マイグループの場合、所属しているか判定
        if (myGroupFlg) {
            CmnMyGroupMsDao mgmsDao = new CmnMyGroupMsDao(con);
            String[] users = new String[]{String.valueOf(userSid)};
            //未所属の場合所属ユーザの先頭を設定
            if (mgmsDao.getMyGroupMsInfo(sessionUsrSid, dspGpSid, users, true).size() < 1) {
                dspUsrSid = Integer.parseInt((userLabel.get(0)).getValue());
            }
        }

        paramMdl.setNtp100SltUser(String.valueOf(dspUsrSid));
    }

    /**
     * <br>[機  能] 表示グループ用のグループリストを取得する
     * <br>[解  説] 管理者設定の共有範囲が「ユーザ全員で共有」の場合有効な全てのグループを取得する。
     * <br>「所属グループ内のみ共有可」の場合、ユーザが所属するグループのみを返す。
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid ユーザSID
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<NtpLabelValueModel> getGroupLabelList(Connection con,
            int usrSid) throws SQLException {

        List < NtpLabelValueModel > labelList = null;

        NtpCommonBiz ntpBiz = new NtpCommonBiz(con, reqMdl__);
        labelList = ntpBiz.getGroupLabelForNippou(
                usrSid, con, true);

        return labelList;
    }


    /**
     * 検索条件部分の開始・終了期間コンボを設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp100ParamModel
     */
    private void __setDateCombo(Ntp100ParamModel paramMdl) {
        //基準日
        String strDspDate = NullDefault.getString(
                paramMdl.getNtp010DspDate(), new UDate().getDateString());
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);
        UDate frDspDate = dspDate.cloneUDate();
        //開始日はデフォルトで一週間前を指定
        frDspDate.addDay(GSConstNippou.SEARCH_FR_DAYS);
        UDate toDspDate = new UDate();
        toDspDate.setDate(strDspDate);
        toDspDate.addYear(1); //基準日から１年後

        //年月日コンボを設定
        paramMdl.setNtp100YearLabel(__getYearLabelList());   //年
        paramMdl.setNtp100MonthLabel(__getMonthLabelList()); //月
        paramMdl.setNtp100DayLabel(__getDayLabelList());     //日

        //期間設定
        paramMdl.setNtp100SltYearFr(
                NullDefault.getString(paramMdl.getNtp100SltYearFr(), frDspDate.getStrYear()));
        paramMdl.setNtp100SltMonthFr(
                NullDefault.getString(paramMdl.getNtp100SltMonthFr(),
                        String.valueOf(frDspDate.getMonth())));
        paramMdl.setNtp100SltDayFr(
                NullDefault.getString(paramMdl.getNtp100SltDayFr(),
                        String.valueOf(frDspDate.getIntDay())));

        paramMdl.setNtp100SltYearTo(
                NullDefault.getString(paramMdl.getNtp100SltYearTo(), toDspDate.getStrYear()));
        paramMdl.setNtp100SltMonthTo(
                NullDefault.getString(paramMdl.getNtp100SltMonthTo(),
                        String.valueOf(toDspDate.getMonth())));
        paramMdl.setNtp100SltDayTo(
                NullDefault.getString(paramMdl.getNtp100SltDayTo(),
                        String.valueOf(toDspDate.getIntDay())));
    }

    /**
     * 年コンボを作成する
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getYearLabelList() {

        int sysYear = (new UDate()).getYear();
        int start = sysYear - 10;
        int end = sysYear + 10;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
              new LabelValueBean(
                 gsMsg.getMessage(
                  "cmn.year", new String[] {String.valueOf(value)}), String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * 月コンボを作成する
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getMonthLabelList() {

        int start = 1;
        int end = 12;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                            value + gsMsg.getMessage("cmn.month"), String.valueOf(value)));
        }
        return labelList;
    }
    /**
     * 日コンボを作成する
     * @return List (in LabelValueBean)  コンボ
     */
    private List < LabelValueBean > __getDayLabelList() {

        int start = 1;
        int end = 31;

        GsMessage gsMsg = new GsMessage(reqMdl__);
        List < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
                    new LabelValueBean(
                             value + gsMsg.getMessage("cmn.day"), String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * 検索条件部分のソートコンボを設定する
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp100ParamModel
     */
    private void __setSortCombo(Ntp100ParamModel paramMdl) {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        //ソートキーラベル
        ArrayList<LabelValueBean> sortLabel = new ArrayList<LabelValueBean>();
        //名前
        String textName = gsMsg.getMessage("cmn.name4");
        //日報日付
        String textStartDate = "報告日付";
        //タイトル/内容
        String textTitleContent = gsMsg.getMessage("schedule.sch100.7");
        String[] listSortKeyAllText = new String[] { textName,
                textStartDate, textTitleContent };
        for (int i = 0; i < GSConstNippou.LIST_SORT_KEY_ALL.length; i++) {
            String label = listSortKeyAllText[i];
            String value = Integer.toString(GSConstNippou.LIST_SORT_KEY_ALL[i]);
            sortLabel.add(new LabelValueBean(label, value));
        }
        paramMdl.setSortKeyList(sortLabel);
    }
    /**
     * ユーザ氏名を取得する
     * <br>[機  能]ユーザ区分がグループの場合はグループ名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param userSid ユーザSID
     * @param con コネクション
     * @return String ユーザ名
     * @throws SQLException SQL実行時例外
     */
    private String __getUserName(int userSid, Connection con)
    throws SQLException {

        String ret = "";

        CmnUsrmInfDao uDao = new CmnUsrmInfDao(con);
        CmnUsrmInfModel uMdl = uDao.select(userSid);
        if (uMdl != null) {
            ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
        }

        return ret;
    }

    /**
     * <br>[機  能]検索条件部分のタイトルカラーコメントを設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl Ntp100ParamModel
     * @param con コネクション
     * @throws SQLException SQL実行時例外
     */
    private void __setColorComment(Ntp100ParamModel paramMdl, Connection con) throws SQLException {

        NtpColMsgDao msgDao = new NtpColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        paramMdl.setNtp100ColorMsgList(msgList);

    }
    /**
     * <br>[機  能] 表示用日報データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpMdl 日報データ
     * @param sessionUsrSid ユーザSID
     * @param grpSid グループSID
     * @param con コネクション
     * @param myGrpHnt マイグループ判定 False:マイグループではない True:マイグループである
     * @return SimpleNippouModel 表示用モデル
     * @throws SQLException SQL実行時例外
     */
    private SimpleNippouModel __getNtpDspMdl(
            NtpDataModel ntpMdl,
            int sessionUsrSid,
            int grpSid,
            Connection con,
            boolean myGrpHnt) throws SQLException {

        SimpleNippouModel dspNtpMdl = new SimpleNippouModel();

        //本人
        dspNtpMdl.setTitle(ntpMdl.getNipTitle());

        dspNtpMdl.setTitle(ntpMdl.getNipTitle());
        dspNtpMdl.setNtpSid(ntpMdl.getNipSid());
        dspNtpMdl.setUserSid(String.valueOf(ntpMdl.getUsrSid()));
        dspNtpMdl.setNtpDateStr(UDateUtil.getYymdJ(ntpMdl.getNipDate(), reqMdl__));
        dspNtpMdl.setNtpDspDateStr(ntpMdl.getNipDate().getStrYear()
                                 + ntpMdl.getNipDate().getStrMonth()
                                 + ntpMdl.getNipDate().getStrDay());
        dspNtpMdl.setTitleColor(ntpMdl.getNipTitleClo());
        dspNtpMdl.setFromDateStr(UDateUtil.getSeparateHM(ntpMdl.getNipFrTime()));
        dspNtpMdl.setToDateStr(UDateUtil.getSeparateHM(ntpMdl.getNipToTime()));
        dspNtpMdl.setDetail(StringUtilHtml.transToHTmlPlusAmparsant(ntpMdl.getNipDetail()));
        dspNtpMdl.setUserName(__getUserName(ntpMdl.getUsrSid(), con));

        Ntp010Biz biz = new Ntp010Biz(con, reqMdl__);

        //確認区分取得
        dspNtpMdl.setNtp_chkKbn(biz.getCheckKbn(ntpMdl.getNipSid(), sessionUsrSid));
        //コメント件数取得
        dspNtpMdl.setNtp_cmtCnt(biz.existComment(ntpMdl.getNipSid()));
        dspNtpMdl.setNtp_cmtkbn(biz.getCommentKbn(dspNtpMdl.getNtp_cmtCnt()));
        //いいね件数取得
        dspNtpMdl.setNtp_goodCnt(biz.existGood(ntpMdl.getNipSid()));
        dspNtpMdl.setNtp_goodkbn(biz.getCommentKbn(dspNtpMdl.getNtp_goodCnt()));

        //案件名取得
        if (ntpMdl.getNanSid() != 0) {
            dspNtpMdl.setAnkenSid(ntpMdl.getNanSid());
            dspNtpMdl.setAnkenName(__getAnkenData(con, ntpMdl.getNanSid()));
        }


        return dspNtpMdl;
    }

    /**
     * <br>[機  能] 活動分類リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getKtbunruiLabelList(Connection con)
    throws SQLException {

        NtpKtbunruiDao bunruiDao = new NtpKtbunruiDao(con);
        List<NtpKtbunruiModel> ret = null;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        Map<Integer, String> ktMap = new HashMap<Integer, String>();

        ret = bunruiDao.select();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        //未設定
        String textNoSet = gsMsg.getMessage("cmn.notset");
        labelList.add(
                new LabelValueBean(textNoSet, "-1"));

        for (NtpKtbunruiModel mdl : ret) {
            labelList.add(
                    new LabelValueBean(mdl.getNkbName(),
                            String.valueOf(mdl.getNkbSid())));

            //活動分類をMAPに格納
            ktMap.put(mdl.getNkbSid(), mdl.getNkbName());
        }
        ktBunruiMap__ = ktMap;

        return labelList;
    }

    /**
     * <br>[機  能] 活動分類を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ktSid 活動分類SID
     * @return 活動分類
     */
    public String getKtbunrui(int ktSid) {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ktBunrui = gsMsg.getMessage("cmn.notset");

        if (ktBunruiMap__ != null && !ktBunruiMap__.isEmpty()) {
            String bunrui = null;
            bunrui = (String) ktBunruiMap__.get(ktSid);
            if (bunrui != null) {
                ktBunrui = bunrui;
            }
        }
        return ktBunrui;
    }

    /**
     * <br>[機  能] 活動方法リストを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList
     * @throws SQLException SQL実行時例外
     */
    public List<LabelValueBean> getKthouhouLabelList(Connection con)
    throws SQLException {

        NtpKthouhouDao houhouDao = new NtpKthouhouDao(con);
        List<NtpKthouhouModel> ret = null;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        Map<Integer, String> ktMap = new HashMap<Integer, String>();

        ret = houhouDao.select();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        //未設定
        String textNoSet = gsMsg.getMessage("cmn.notset");
        labelList.add(
                new LabelValueBean(textNoSet, "-1"));

        for (NtpKthouhouModel mdl : ret) {
            labelList.add(
                    new LabelValueBean(mdl.getNkhName(),
                            String.valueOf(mdl.getNkhSid())));

            //活動方法をMAPに格納
            ktMap.put(mdl.getNkhSid(), mdl.getNkhName());
        }
        ktHouhouMap__ = ktMap;

        return labelList;
    }

    /**
     * <br>[機  能] 活動方法を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ktSid 活動方法SID
     * @return 活動方法
     */
    public String getKthouhou(int ktSid) {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ktHouhou = gsMsg.getMessage("cmn.notset");

        if (ktHouhouMap__ != null && !ktHouhouMap__.isEmpty()) {
            String houhou = null;
            houhou = ktHouhouMap__.get(ktSid);
            if (houhou != null) {
                ktHouhou = houhou;
            }
        }
        return ktHouhou;
    }

    /**
     * <br>[機  能] DBから案件情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param nanSid 案件SID
     * @throws SQLException SQL実行時例外
     * @return ankenName 案件名
     */
    private String __getAnkenData(Connection con,
                              int nanSid) throws SQLException {

        String ankenName = null;

        //案件情報
        NtpAnkenDao ankenDao = new NtpAnkenDao(con);
        NtpAnkenModel ankenModel = ankenDao.select(nanSid);

        if (ankenModel != null) {
            ankenName = ankenModel.getNanName();
        }
        return ankenName;
    }

    /**
     * <br>[機  能] CSV出力のデフォルト値を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @return String[] デフォルトCSV出力項目
     */
    public static String[] getDefultCsvOut() {
        String[] csvOut = {
                GSConstNippou.CSV_OUT_USR_ID,
                GSConstNippou.CSV_OUT_NTP_DATE,
                GSConstNippou.CSV_OUT_NTP_FR_TIME,
                GSConstNippou.CSV_OUT_NTP_TO_TIME,
                GSConstNippou.CSV_OUT_NAN_CODE,
                GSConstNippou.CSV_OUT_ACO_CODE,
                GSConstNippou.CSV_OUT_TITLE,
                GSConstNippou.CSV_OUT_TITLE_COLOR,
                GSConstNippou.CSV_OUT_KTB_CODE,
                GSConstNippou.CSV_OUT_KTH_CODE,
                GSConstNippou.CSV_OUT_VALUE,
                GSConstNippou.CSV_OUT_MIKOMIDO
        };
        return csvOut;
    }
}