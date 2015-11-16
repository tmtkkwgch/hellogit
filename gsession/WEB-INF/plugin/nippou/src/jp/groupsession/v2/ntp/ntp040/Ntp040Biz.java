package jp.groupsession.v2.ntp.ntp040;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import jp.co.sjts.util.Encoding;
import jp.co.sjts.util.FileNameUtil;
import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOTools;
import jp.co.sjts.util.io.IOToolsException;
import jp.groupsession.v2.adr.dao.AdrCompanyBaseDao;
import jp.groupsession.v2.adr.dao.AdrCompanyDao;
import jp.groupsession.v2.adr.model.AdrCompanyBaseModel;
import jp.groupsession.v2.adr.model.AdrCompanyModel;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.cmn.GSConstCommon;
import jp.groupsession.v2.cmn.GSConstSchedule;
import jp.groupsession.v2.cmn.GSValidateUtil;
import jp.groupsession.v2.cmn.biz.CommonBiz;
import jp.groupsession.v2.cmn.biz.GroupBiz;
import jp.groupsession.v2.cmn.config.PluginConfig;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.dao.GroupDao;
import jp.groupsession.v2.cmn.dao.MlCountMtController;
import jp.groupsession.v2.cmn.dao.UserSearchDao;
import jp.groupsession.v2.cmn.dao.base.CmnBelongmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmDao;
import jp.groupsession.v2.cmn.dao.base.CmnUsrmInfDao;
import jp.groupsession.v2.cmn.exception.TempFileException;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.cmn.model.base.CmnBinfModel;
import jp.groupsession.v2.cmn.model.base.CmnUsrmInfModel;
import jp.groupsession.v2.ntp.GSConstNippou;
import jp.groupsession.v2.ntp.biz.NtpCommonBiz;
import jp.groupsession.v2.ntp.dao.NippouSearchDao;
import jp.groupsession.v2.ntp.dao.NtpAnkenDao;
import jp.groupsession.v2.ntp.dao.NtpBinDao;
import jp.groupsession.v2.ntp.dao.NtpCheckDao;
import jp.groupsession.v2.ntp.dao.NtpColMsgDao;
import jp.groupsession.v2.ntp.dao.NtpCommentDao;
import jp.groupsession.v2.ntp.dao.NtpDataDao;
import jp.groupsession.v2.ntp.dao.NtpGoodDao;
import jp.groupsession.v2.ntp.dao.NtpMikomidoMsgDao;
import jp.groupsession.v2.ntp.dao.NtpPriTargetDao;
import jp.groupsession.v2.ntp.dao.NtpTargetDao;
import jp.groupsession.v2.ntp.model.NippouSearchModel;
import jp.groupsession.v2.ntp.model.NtpAdmConfModel;
import jp.groupsession.v2.ntp.model.NtpAnkenModel;
import jp.groupsession.v2.ntp.model.NtpBinModel;
import jp.groupsession.v2.ntp.model.NtpCommentModel;
import jp.groupsession.v2.ntp.model.NtpDataModel;
import jp.groupsession.v2.ntp.model.NtpGoodModel;
import jp.groupsession.v2.ntp.model.NtpKtbunruiModel;
import jp.groupsession.v2.ntp.model.NtpKthouhouModel;
import jp.groupsession.v2.ntp.model.NtpMikomidoMsgModel;
import jp.groupsession.v2.ntp.model.NtpPriConfModel;
import jp.groupsession.v2.ntp.model.NtpPriTargetModel;
import jp.groupsession.v2.ntp.model.NtpTargetModel;
import jp.groupsession.v2.ntp.model.NtpTemplateModel;
import jp.groupsession.v2.ntp.ntp010.Ntp010Biz;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040AddressModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040CommentModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040DataModel;
import jp.groupsession.v2.ntp.ntp040.model.Ntp040DspCommentModel;
import jp.groupsession.v2.ntp.ntp170.Ntp170Dao;
import jp.groupsession.v2.ntp.ntp180.Ntp180Dao;
import jp.groupsession.v2.prj.GSConstProject;
import jp.groupsession.v2.prj.dao.PrjTododataDao;
import jp.groupsession.v2.prj.dao.ProjectSearchDao;
import jp.groupsession.v2.prj.model.PrjTododataModel;
import jp.groupsession.v2.prj.model.ProjectItemModel;
import jp.groupsession.v2.prj.model.ProjectSearchModel;
import jp.groupsession.v2.sch.dao.SchCompanyDao;
import jp.groupsession.v2.sch.dao.ScheduleSearchDao;
import jp.groupsession.v2.sch.model.SchCompanyModel;
import jp.groupsession.v2.sch.model.SchDataModel;
import jp.groupsession.v2.sch.model.ScheduleSearchModel;
import jp.groupsession.v2.struts.msg.GsMessage;
import jp.groupsession.v2.usr.GSConstUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] 日報 日報登録画面のビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Ntp040Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Ntp040Biz.class);
    /** DBコネクション */
    public Connection con__ = null;
    /** リクエスモデル */
    public RequestModel reqMdl__ = null;
    /** 採番コントローラ */
    public MlCountMtController cntCon__ = null;
    /** 活動分類格納MAP */
    public Map<Integer, String> ktBunruiMap__ = null;
    /** 活動方法格納MAP */
    public Map<Integer, String> ktHouhouMap__ = null;
    /** アドレス帳履歴最大件数 */
    public int adrHistoryLimit__ = 10;
    /** 案件履歴最大件数 */
    public int ankenHistoryLimit__ = 10;
    /** コピーフラグ */
    public boolean copyFlg__ = false;

    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     */
    public Ntp040Biz(Connection con, RequestModel reqMdl) {
        con__ = con;
        reqMdl__ = reqMdl;
    }
    /**
     * <p>コンストラクタ
     * @param con Connection
     * @param reqMdl RequestModel
     * @param cntCon MlCountMtController
     */
    public Ntp040Biz(Connection con, RequestModel reqMdl, MlCountMtController cntCon) {
        con__ = con;
        reqMdl__ = reqMdl;
        cntCon__ = cntCon;
    }
    /**
     * <br>[機  能] 初期表示画面情報を取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl アクションフォーム
     * @param pconfig プラグインコンフィグ
     * @param con コネクション
     * @return Ntp010Form アクションフォーム
     * @throws Exception 実行例外
     * @throws NumberFormatException 実行例外
     */
    public Ntp040ParamModel getInitData(Ntp040ParamModel paramMdl,
            PluginConfig pconfig, Connection con)
    throws NumberFormatException, Exception {

        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        Ntp010Biz ntp010biz = new Ntp010Biz(con__, reqMdl__);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        //個人設定を取得
        NtpPriConfModel confMdl = ntp010biz.getPrivateConf(sessionUsrSid, con);
        //管理者設定を取得
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        NtpAdmConfModel adminConf = biz.getAdminConfiModel(con);

        //リクエストパラメータを取得
        //表示開始日
        if (StringUtil.isNullZeroStringSpace(paramMdl.getNtp010DspDate())) {
            paramMdl.setNtp010DspDate(new UDate().getDateString());
        }
        String strDspDate = NullDefault.getString(
                paramMdl.getNtp010DspDate(), new UDate().getDateString());
        UDate dspDate = new UDate();
        dspDate.setDate(strDspDate);

        //年月日初期選択値
        UDate uDate = new UDate();

        if (StringUtil.isNullZeroStringSpace(paramMdl.getNtp010SelectDate())) {
            paramMdl.setNtp010SelectDate(uDate.getDateString());
        }

        uDate.setDate(
                NullDefault.getString(
                        paramMdl.getNtp010SelectDate(), uDate.getDateString()));
        paramMdl.setNtp040InitYear(String.valueOf(uDate.getYear()));
        paramMdl.setNtp040InitMonth(String.valueOf(uDate.getMonth()));
        paramMdl.setNtp040InitDay(String.valueOf(uDate.getIntDay()));

        //曜日取得
        if (uDate.getWeek() == 1) {
            paramMdl.setNtp040DspDateKbn("ntp_date_sun");
        } else if (uDate.getWeek() == 7) {
            paramMdl.setNtp040DspDateKbn("ntp_date_sat");
        } else {
            paramMdl.setNtp040DspDateKbn("ntp_date_def");
        }

        paramMdl.setNtp040DspDateKbnStr(getStrWeek(uDate.getWeek()));

        //活動分類取得
        paramMdl.setNtp040KtbunruiLavel(getKtbunruiLabelList(con));
        if (paramMdl.getNtp040KtbunruiLavel() != null) {
            if (paramMdl.getNtp040KtbunruiLavel().size() > 0) {
                String ktbunruiStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040KtbunruiLavel()) {
                    ktbunruiStr +=
                   "<input type=\"hidden\" class=\"ktbunruiclass\" name=\"ktbunruivalue\" value=\""
                        + bean.getValue() + "_"
                        + StringUtilHtml.transToHTmlPlusAmparsant(bean.getLabel()) + "\">";
                }
                paramMdl.setNtp040KtbunruiLavelStr(ktbunruiStr);
            }
        }


        //活動方法取得
        paramMdl.setNtp040KthouhouLavel(getKthouhouLabelList(con));
        if (paramMdl.getNtp040KthouhouLavel() != null) {
            if (paramMdl.getNtp040KthouhouLavel().size() > 0) {
                String kthouhouStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040KthouhouLavel()) {
                    kthouhouStr +=
                  "<input type=\"hidden\" class=\"kthouhouclass\" name=\"kthouhouvalue\" value=\""
                        + bean.getValue() + "_"
                        + StringUtilHtml.transToHTmlPlusAmparsant(bean.getLabel()) + "\">";
                }
                paramMdl.setNtp040KthouhouLavelStr(kthouhouStr);
            }
        }


        //表示項目設定
        String cmd = NullDefault.getString(paramMdl.getCmd(), "");

        if (cmd.equals(GSConstNippou.CMD_COPY)) {
            copyFlg__ = true;
        }

        if (cmd.equals(GSConstNippou.CMD_ADD)) {
        //新規モード
            int iniPub = 0;
            int iniFcolor = GSConstNippou.DF_BG_COLOR;
            int iniEdit = GSConstNippou.EDIT_CONF_NONE;
            if (confMdl != null) {

                if (NullDefault.getString(paramMdl.getNtp010SelectUsrKbn(), "").equals(
                        String.valueOf(GSConstNippou.USER_KBN_GROUP))
                        && iniPub != GSConstNippou.DSP_PUBLIC
                        && iniPub != GSConstNippou.DSP_NOT_PUBLIC) {
                    iniPub = GSConstNippou.DSP_PUBLIC;
                }

                iniFcolor = confMdl.getNprIniFcolor();
            }
            if (iniFcolor < 1 || 5 < iniFcolor) {
                iniFcolor = GSConstNippou.DF_BG_COLOR;
            }

            //名前
            String uid = NullDefault.getStringZeroLength(paramMdl.getNtp010SelectUsrSid(), "-1");
            String ukb = NullDefault.getStringZeroLength(paramMdl.getNtp010SelectUsrKbn(), "0");
            log__.debug("uid=" + uid);
            log__.debug("ukb=" + ukb);
            paramMdl.setNtp040UsrName(
                    getUsrName(Integer.parseInt(uid), Integer.parseInt(ukb), con));
            //登録者
            paramMdl.setNtp040AddUsrName(getUsrName(sessionUsrSid, Integer.parseInt(ukb), con));

            //見込み度
            paramMdl.setNtp040Mikomido(String.valueOf(GSConstNippou.DF_MIKOMIDO));

            //背景色
            paramMdl.setNtp040Bgcolor(NullDefault.getString(
                    paramMdl.getNtp040Bgcolor(), String.valueOf(iniFcolor)));
            paramMdl.setNtp040BgcolorInit(String.valueOf(iniFcolor));

            //公開非公開
            paramMdl.setNtp040Public(NullDefault.getString(
                    paramMdl.getNtp040Public(), String.valueOf(iniPub)));
            paramMdl.setNtp040Edit(NullDefault.getString(
                    paramMdl.getNtp040Edit(), String.valueOf(iniEdit)));

            //デフォルト表示グループ
            NtpCommonBiz scBiz = new NtpCommonBiz(con__, reqMdl__);
            String dfGpSidStr = scBiz.getDispDefaultGroupSidStr(con, sessionUsrSid);

            //表示グループ
            String dspGpSidStr = NullDefault.getString(paramMdl.getNtp040GroupSid(), dfGpSidStr);
            if (NtpCommonBiz.isMyGroupSid(dspGpSidStr)) {
                paramMdl.setNtp040GroupSid(dspGpSidStr);
            } else {
                paramMdl.setNtp040GroupSid(dspGpSidStr);
            }

            //案件履歴選択時
            if (!StringUtil.isNullZeroStringSpace(paramMdl.getNtp010HistoryAnkenSid())
                    && GSValidateUtil.isNumber(paramMdl.getNtp010HistoryAnkenSid())) {

                paramMdl.setNtp040AnkenSid(Integer.valueOf(paramMdl.getNtp010HistoryAnkenSid()));

                //案件情報
                NtpAnkenDao ankenDao = new NtpAnkenDao(con);
                NtpAnkenModel ankenModel = ankenDao.select(paramMdl.getNtp040AnkenSid());

                if (ankenModel != null) {
                    paramMdl.setNtp040AnkenCode(ankenModel.getNanCode());
                    paramMdl.setNtp040AnkenName(ankenModel.getNanName());
                    paramMdl.setNtp040Title(ankenModel.getNanName());
                    paramMdl.setNtp040Mikomido(String.valueOf(ankenModel.getNanMikomi()));
                    if (ankenModel.getAcoSid() > 0) {
                        paramMdl.setNtp010HistoryCompSid(
                                String.valueOf(ankenModel.getAcoSid()));
                        if (ankenModel.getAbaSid() > 0) {
                            paramMdl.setNtp010HistoryCompBaseSid(
                                    String.valueOf(ankenModel.getAbaSid()));
                        }
                    }
                }
            }

            //企業・顧客履歴選択時
            if (!StringUtil.isNullZeroStringSpace(paramMdl.getNtp010HistoryCompSid())
                    && GSValidateUtil.isNumber(paramMdl.getNtp010HistoryCompSid())) {

                paramMdl.setNtp040CompanySid(Integer.valueOf(paramMdl.getNtp010HistoryCompSid()));

                //会社情報
                AdrCompanyDao companyDao = new AdrCompanyDao(con);
                AdrCompanyModel companyModel = companyDao.select(paramMdl.getNtp040CompanySid());
                if (companyModel != null) {
                    paramMdl.setNtp040CompanyCode(companyModel.getAcoCode());
                    paramMdl.setNtp040CompanyName(companyModel.getAcoName());
                    paramMdl.setNtp040Title(companyModel.getAcoName());
                    //会社拠点情報
                    if (!StringUtil.isNullZeroStringSpace(paramMdl.getNtp010HistoryCompBaseSid())
                            && GSValidateUtil.isNumber(paramMdl.getNtp010HistoryCompBaseSid())) {

                        paramMdl.setNtp040CompanyBaseSid(
                                Integer.valueOf(paramMdl.getNtp010HistoryCompBaseSid()));

                        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
                        AdrCompanyBaseModel companyBaseMdl = new AdrCompanyBaseModel();
                        companyBaseMdl = companyBaseDao.select(paramMdl.getNtp040CompanyBaseSid());
                        if (companyBaseMdl != null) {
                            paramMdl.setNtp040CompanyBaseName(companyBaseMdl.getAbaName());
                            paramMdl.setNtp040Title(companyModel.getAcoName()
                                    + " " + companyBaseMdl.getAbaName());
                        }
                    }
                }
            }
        } else if (cmd.equals(GSConstNippou.CMD_EDIT)
                || cmd.equals(GSConstNippou.CMD_COPY)
                || cmd.equals(GSConstNippou.CMD_PDF)) {
//----------修正モード------------------------------------------------------//


            NippouSearchModel ntpPrevMdl = null;
            ntpPrevMdl = getNtpDataPrevNext(uDate, paramMdl, con, 0);
            NippouSearchModel ntpTodayMdl = null;
            ntpTodayMdl = getNtpDataPrevNext(uDate, paramMdl, con, -1);
            NippouSearchModel ntpNextMdl = null;
            ntpNextMdl = getNtpDataPrevNext(uDate, paramMdl, con, 1);


            //前日の日報データを取得
            if (ntpPrevMdl != null) {
                paramMdl.setNtp040PrevNtpSid(ntpPrevMdl.getNipSid());
                paramMdl.setNtp040PrevNtpDate(ntpPrevMdl.getNipDate().getDateString());
            } else {
                UDate prevDate = uDate.cloneUDate();
                prevDate.addDay(-1);
                paramMdl.setNtp040PrevNtpSid(0);
                paramMdl.setNtp040PrevNtpDate(prevDate.getDateString());
            }

            //今日の日報データを取得
            if (ntpTodayMdl != null) {
                paramMdl.setNtp040TodayNtpSid(ntpTodayMdl.getNipSid());
                paramMdl.setNtp040TodayNtpDate(ntpTodayMdl.getNipDate().getDateString());
            } else {
                UDate todayDate = new UDate();
                paramMdl.setNtp040TodayNtpSid(0);
                paramMdl.setNtp040TodayNtpDate(todayDate.getDateString());
            }

            //翌日の日報データを取得
            if (ntpNextMdl != null) {
                paramMdl.setNtp040NextNtpSid(ntpNextMdl.getNipSid());
                paramMdl.setNtp040NextNtpDate(ntpNextMdl.getNipDate().getDateString());
            } else {
                UDate nextDate = uDate.cloneUDate();
                nextDate.addDay(1);
                paramMdl.setNtp040NextNtpSid(0);
                paramMdl.setNtp040NextNtpDate(nextDate.getDateString());
            }

            //日報SID保存リスト
            List<Integer> ntpSids = new ArrayList<Integer>();

            String ntpSid = NullDefault.getString(paramMdl.getNtp010NipSid(), "-1");

            //日報SIDがない場合は日付のみを設定
            if (ntpSid.equals("0")) {
                paramMdl.setNtp040FrYear(String.valueOf(uDate.getYear()));
                paramMdl.setNtp040FrMonth(String.valueOf(uDate.getMonth()));
                paramMdl.setNtp040FrDay(String.valueOf(uDate.getIntDay()));
            }


            GsMessage gsMsg = new GsMessage(reqMdl__);
            //選択された日付の日報データをすべて取得する

            //日報データ取得
            ArrayList<NippouSearchModel> ntpMdlList = new ArrayList<NippouSearchModel>();

            if (cmd.equals(GSConstNippou.CMD_EDIT)
                    || cmd.equals(GSConstNippou.CMD_PDF)) {
                //確認・編集
                ntpMdlList = getNtpData(Integer.parseInt(ntpSid), adminConf, con, paramMdl);
            } else {
                //複写して登録
                ntpMdlList = getNtpDataSingle(Integer.parseInt(ntpSid), adminConf, con, paramMdl);
                paramMdl.setNtp010SelectUsrSid(String.valueOf(sessionUsrSid));
            }

            String uid = paramMdl.getNtp010SelectUsrSid();

            //登録・編集権限を取得
            paramMdl.setAuthAddEditKbn(
                    ntp010biz.isAddEditOk(Integer.valueOf(uid), con));

            //目標編集権限
            paramMdl.setNtp040TargetAdmKbn(
                    ntp010biz.isAddEditOk(Integer.valueOf(uid), con));

            //日報データリスト
            ArrayList<Ntp040DataModel> dataList = new ArrayList<Ntp040DataModel>();

            if (!ntpMdlList.isEmpty()) {

                //データセット
                for (NippouSearchModel ntpMdl : ntpMdlList) {

                    if (ntpMdl == null) {
                        return paramMdl;
                    }

                    ntpSids.add(ntpMdl.getNipSid());

                    //複写して登録の場合は登録者をセッションユーザにする
                    if (cmd.equals(GSConstNippou.CMD_COPY)) {
                        ntpMdl.setNipAuid(Integer.valueOf(uid));
                    }

                    Ntp040DataModel dataMdl = new Ntp040DataModel();

                    if (String.valueOf(ntpMdl.getNipSid()).equals(ntpSid)) {
                        dataMdl.setNtp040SelectFlg(1);
                    }

                    dataMdl.setNtp040NtpSid(ntpMdl.getNipSid());

                    CmnUsrmInfModel uMdl = null;

                    UserSearchDao uDao = new UserSearchDao(con);
                    CmnUsrmDao cuDao = new CmnUsrmDao(con);
                    //登録者
                    uMdl = uDao.getUserInfoJtkb(ntpMdl.getNipAuid(), -1);
                    if (uMdl != null) {
                        ntpMdl.setNipAuidSei(uMdl.getUsiSei());
                        dataMdl.setNtp040NtpUsiSei(uMdl.getUsiSei());
                        ntpMdl.setNipAuidMei(uMdl.getUsiMei());
                        dataMdl.setNtp040NtpUsiMei(uMdl.getUsiMei());
                        ntpMdl.setNipAuidJkbn(cuDao.getUserJkbn(ntpMdl.getNipAuid()));
                        dataMdl.setNtp040NtpUsiId(uMdl.getUsrSid());
                    }


                    //ユーザ情報取得
                    CmnUsrmInfDao dao = new CmnUsrmInfDao(con);
                    CmnUsrmInfModel ntpUsrMdl = new CmnUsrmInfModel();

                    //ユーザSIDをセット
                    ntpUsrMdl.setUsrSid(
                            Integer.parseInt(
                                    NullDefault.getString(uid, "-1")));
                    ntpUsrMdl = dao.select(ntpUsrMdl);

                    //ntpUsrMdl = getUsrInf(Integer.parseInt(uid), con);
                    if (ntpUsrMdl != null) {
                        paramMdl.setNtp040UsrName(
                                ntpUsrMdl.getUsiSei() + " " + ntpUsrMdl.getUsiMei());
                        paramMdl.setNtp040UsrBinSid(ntpUsrMdl.getBinSid());
                        paramMdl.setNtp040UsrPctKbn(ntpUsrMdl.getUsiPictKf());
                    }

                    paramMdl.setNtp040AddUsrName(
                            ntpMdl.getNipAuidSei() + " " + ntpMdl.getNipAuidMei());
                    dataMdl.setNtp040NtpAddUsrName(
                            ntpMdl.getNipAuidSei() + " " + ntpMdl.getNipAuidMei());


                    paramMdl.setNtp040AddUsrJkbn(String.valueOf(
                            ntpMdl.getNipAuidJkbn()));
                    dataMdl.setNtp040AddUsrJkbn(ntpMdl.getNipAuidJkbn());

                    //登録日時
                    String textAddDate = gsMsg.getMessage("schedule.src.84");
                    paramMdl.setNtp040AddDate(
                            textAddDate + " : "
                            + UDateUtil.getSlashYYMD(ntpMdl.getNipAdate())
                            + " "
                            + UDateUtil.getSeparateHM(ntpMdl.getNipAdate()));
                    dataMdl.setNtp040NtpDate(textAddDate + " : "
                            + UDateUtil.getSlashYYMD(ntpMdl.getNipAdate())
                            + " "
                            + UDateUtil.getSeparateHM(ntpMdl.getNipAdate()));

                    UDate ntpDate = ntpMdl.getNipDate();
                    UDate frDate = ntpMdl.getNipFrTime();
                    UDate toDate = ntpMdl.getNipToTime();

                    //開始年月日
                    paramMdl.setNtp040FrYear(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040FrYear(),
                                    String.valueOf(ntpDate.getYear())));
                    paramMdl.setNtp040FrMonth(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040FrMonth(),
                                    String.valueOf(ntpDate.getMonth())));
                    paramMdl.setNtp040FrDay(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040FrDay(),
                                    String.valueOf(ntpDate.getIntDay())));

                    dataMdl.setNtpYear(ntpDate.getYear());
                    dataMdl.setNtpMonth(ntpDate.getMonth());
                    dataMdl.setNtpDay(ntpDate.getIntDay());

                    //活動分類
                    paramMdl.setNtp040Ktbunrui(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040Ktbunrui(),
                                    String.valueOf(ntpMdl.getMkbSid())));

                    dataMdl.setKtbunruiSid(ntpMdl.getMkbSid());
                    dataMdl.setNtp040DspKtbunrui(getKtbunrui(ntpMdl.getMkbSid()));


                    //活動方法
                    paramMdl.setNtp040Kthouhou(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040Kthouhou(),
                            String.valueOf(ntpMdl.getMkhSid())));
                    dataMdl.setKthouhouSid(ntpMdl.getMkhSid());
                    dataMdl.setNtp040DspKthouhou(getKthouhou(ntpMdl.getMkhSid()));


                    //見込み度
                    paramMdl.setNtp040Mikomido(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040Mikomido(),
                            String.valueOf(ntpMdl.getNipMikomi())));
                    dataMdl.setMikomido(ntpMdl.getNipMikomi());
                    dataMdl.setNtp040DspMikomido(getMikomido(ntpMdl.getNipMikomi()));


                    if (frDate.equalsDate(ntpMdl.getNipDate())) {
                        dataMdl.setFrHour(frDate.getIntHour());
                        dataMdl.setNtp040DspFrHour(
                                StringUtil.toDecFormat(frDate.getIntHour(), "00"));
                        paramMdl.setNtp040FrHour(String.valueOf(frDate.getIntHour()));
                    } else {
                        dataMdl.setFrHour(frDate.getIntHour() + 24);
                        dataMdl.setNtp040DspFrHour(
                                StringUtil.toDecFormat(frDate.getIntHour() + 24, "00"));
                        paramMdl.setNtp040FrHour(String.valueOf(frDate.getIntHour() + 24));
                    }


                    paramMdl.setNtp040FrMin(
                            NullDefault.getStringZeroLength(String.valueOf(frDate.getIntMinute()),
                                    GSConstNippou.TIME_NOT_SELECT));
                    dataMdl.setFrMin(frDate.getIntMinute());
                    dataMdl.setNtp040DspFrMinute(
                            StringUtil.toDecFormat(frDate.getIntMinute(), "00"));


                    paramMdl.setNtp040ToHour(
                            NullDefault.getStringZeroLength(paramMdl.getNtp040ToHour(),
                                    GSConstNippou.TIME_NOT_SELECT));


                    if (frDate.equalsDate(toDate)
                            && ntpMdl.getNipDate().equalsDate(frDate)) {
                        dataMdl.setToHour(toDate.getIntHour());
                        dataMdl.setNtp040DspToHour(
                                StringUtil.toDecFormat(toDate.getIntHour(), "00"));
                        paramMdl.setNtp040ToHour(String.valueOf(toDate.getIntHour()));
                    } else {
                        dataMdl.setToHour(toDate.getIntHour() + 24);
                        dataMdl.setNtp040DspToHour(
                                StringUtil.toDecFormat(toDate.getIntHour() + 24, "00"));
                        paramMdl.setNtp040ToHour(String.valueOf(toDate.getIntHour() + 24));
                    }

                    dataMdl.setToMin(toDate.getIntMinute());
                    dataMdl.setNtp040DspToMinute(
                            StringUtil.toDecFormat(toDate.getIntMinute(), "00"));

                    paramMdl.setNtp040ToMin(
                            NullDefault.getStringZeroLength(String.valueOf(toDate.getIntMinute()),
                                    GSConstNippou.TIME_NOT_SELECT));


                    //背景
                    int iniBgcolor = GSConstNippou.DF_BG_COLOR;
                    if (ntpMdl.getNipTitleClo() > GSConstNippou.DF_BG_COLOR) {
                        iniBgcolor = ntpMdl.getNipTitleClo();
                    }
                    paramMdl.setNtp040Bgcolor(
                            NullDefault.getString(
                                    paramMdl.getNtp040Bgcolor(), String.valueOf(iniBgcolor)));
                    dataMdl.setBgcolor(iniBgcolor);


                    //タイトル
                    paramMdl.setNtp040Title(
                            NullDefault.getString(paramMdl.getNtp040Title(), ntpMdl.getNipTitle()));
                    dataMdl.setTitle(ntpMdl.getNipTitle());

                    //詳細
                    paramMdl.setNtp040Value(
                            NullDefault.getString(
                                    paramMdl.getNtp040Value(), ntpMdl.getNipDetail()));
                    dataMdl.setValueStr(ntpMdl.getNipDetail());
                    String dspValueStr = StringUtil.transToLink(StringUtilHtml.returntoBR(
                            StringUtilHtml.transToHTmlForTextArea(ntpMdl.getNipDetail())),
                            StringUtil.OTHER_WIN, true);


                    dataMdl.setNtp040DspValueStr(StringUtilHtml.returntoBR(dspValueStr));

                    //次のアクション区分
                    paramMdl.setNtp040ActDateKbn(ntpMdl.getNipActDateKbn());
                    dataMdl.setActDateKbn(ntpMdl.getNipActDateKbn());

                    if (ntpMdl.getNipActDateKbn() != 0) {
                        if (ntpMdl.getNipActionDate() != null) {
                            UDate ntpActionDate = ntpMdl.getNipActionDate();

                            //次のアクション日
                            paramMdl.setNtp040NxtActYear(
                                    NullDefault.getStringZeroLength(paramMdl.getNtp040NxtActYear(),
                                            String.valueOf(ntpActionDate.getYear())));
                            paramMdl.setNtp040NxtActMonth(
                                    NullDefault.getStringZeroLength(paramMdl.getNtp040NxtActMonth(),
                                            String.valueOf(ntpActionDate.getMonth())));
                            paramMdl.setNtp040NxtActDay(
                                    NullDefault.getStringZeroLength(paramMdl.getNtp040NxtActDay(),
                                            String.valueOf(ntpActionDate.getIntDay())));

                            dataMdl.setActionYear(ntpActionDate.getYear());
                            dataMdl.setActionMonth(ntpActionDate.getMonth());
                            dataMdl.setActionDay(ntpActionDate.getIntDay());
                        }
                    }

                    //次のアクション
                    paramMdl.setNtp040NextAction(
                            NullDefault.getString(
                                    paramMdl.getNtp040NextAction(), ntpMdl.getNipAction()));
                    dataMdl.setActionStr(ntpMdl.getNipAction());
                    String dspActionStr =
                        StringUtilHtml.transToHTmlForTextArea(ntpMdl.getNipAction());
                    dataMdl.setNtp040DspActionStr(StringUtilHtml.returntoBR(dspActionStr));


                    //所感
                    paramMdl.setNtp040Biko(
                            NullDefault.getString(paramMdl.getNtp040Biko(), ntpMdl.getNipSyokan()));

                    //公開
                    paramMdl.setNtp040Public(
                            NullDefault.getString(paramMdl.getNtp040Public(),
                                    String.valueOf(ntpMdl.getNipPublic())));
                    //編集権限
                    paramMdl.setNtp040Edit(
                            NullDefault.getString(paramMdl.getNtp040Edit(),
                                    String.valueOf(ntpMdl.getNipEdit())));

                    //拡張SID存在フラグ
                    boolean textDspFlg = false;
                    paramMdl.setNtp040ExTextDspFlg(textDspFlg);

                    //案件情報取得
                    _getAnkenData(con, ntpMdl.getNanSid(), dataMdl);

                    //会社情報、アドレス帳情報を設定
                    _readCompanyData(con, ntpMdl.getAcoSid(), ntpMdl.getAbaSid(), dataMdl);

                    if (cmd.equals(GSConstNippou.CMD_COPY)) {
                        if (dataMdl.getAnkenSid() != 0 && dataMdl.getAnkenSid() != -1) {
                            paramMdl.setNtp040AnkenSid(dataMdl.getAnkenSid());
                            paramMdl.setNtp040AnkenName(dataMdl.getAnkenName());
                            paramMdl.setNtp040AnkenCode(dataMdl.getAnkenCode());
                        } else {
                            paramMdl.setNtp040AnkenSid(-1);
                        }

                        if (dataMdl.getCompanySid() != 0 && dataMdl.getCompanySid() != -1) {
                            paramMdl.setNtp040CompanySid(dataMdl.getCompanySid());
                            paramMdl.setNtp040CompanyName(dataMdl.getCompanyName());
                            paramMdl.setNtp040CompanyCode(dataMdl.getCompanyCode());
                            if (dataMdl.getCompanyBaseSid() != 0
                                    && dataMdl.getCompanyBaseSid() != -1) {
                                paramMdl.setNtp040CompanyBaseSid(dataMdl.getCompanyBaseSid());
                                paramMdl.setNtp040CompanyBaseName(dataMdl.getCompanyBaseName());
                            } else {
                                paramMdl.setNtp040CompanyBaseSid(-1);
                            }
                        } else {
                            paramMdl.setNtp040CompanySid(-1);
                        }

                        paramMdl.setCmd(GSConstNippou.CMD_ADD);
                    }

                    //背景色(初期値)
                    int iniFcolor = GSConstNippou.DF_BG_COLOR;
                    if (confMdl != null) {
                        iniFcolor = confMdl.getNprIniFcolor();
                    }
                    if (iniFcolor < 1 || 5 < iniFcolor) {
                        iniFcolor = GSConstNippou.DF_BG_COLOR;
                    }
                    paramMdl.setNtp040BgcolorInit(String.valueOf(iniFcolor));


                    //ユーザテンプレートデータ取得
                    NtpTemplateModel tmpMdl = null;
                    if (paramMdl.getNtp010SelectUsrSid() != null) {
                        tmpMdl = biz.getUsrTemplate(
                                con, Integer.valueOf(paramMdl.getNtp010SelectUsrSid()));
                    }

                    if (tmpMdl == null) {
                        tmpMdl = new NtpTemplateModel();
                        tmpMdl.setNttTemp(GSConstNippou.ITEM_USE);
                    }

                    //添付ファイル情報取得
                    if (tmpMdl.getNttTemp() == GSConstNippou.ITEM_USE) {
                        NtpBinDao binDao = new NtpBinDao(con);
                        ArrayList<CmnBinfModel> retBin = binDao.getFileList(ntpMdl.getNipSid());
                        paramMdl.setNtp040FileList(retBin);
                        dataMdl.setNtp040FileList(retBin);
                    }


                    //コメント取得
                    Ntp040Dao ntpDao = new Ntp040Dao(con);

                    int authFlg = 0;
                    if (paramMdl.getAuthAddEditKbn() == 0) {
                        authFlg = 1;
                    }

                    ArrayList<Ntp040CommentModel> npcList
                                        = ntpDao.getNpcList(reqMdl__, ntpMdl.getNipSid(),
                                                authFlg);
                    if (!npcList.isEmpty()) {
                        dataMdl.setNtp040CommentList(npcList);
                    }

                    //いいね件数取得
                    NtpGoodDao gDao = new NtpGoodDao(con__);
                    ArrayList<NtpGoodModel> gList = new ArrayList<NtpGoodModel>();
                    gList = gDao.select(ntpMdl.getNipSid());
                    dataMdl.setNtp040GoodCnt(gList.size());

                    //セッションユーザがいいねしているか
                    int goodFlg = 0;
                    for (NtpGoodModel gMdl : gList) {
                        if (gMdl.getUsrSid() == sessionUsrSid) {
                            goodFlg = 1;
                        }
                    }
                    dataMdl.setNtp040GoodFlg(goodFlg);
                    paramMdl.setNtp040DataFlg(true);
                    dataList.add(dataMdl);

                }

                paramMdl.setNtp040DataModelList(dataList);

            }

            //表示する日報をすべて確認済にする
            if (cmd.equals(GSConstNippou.CMD_EDIT)) {
                if (!ntpSids.isEmpty()) {
                    setCheck(ntpSids, sessionUsrSid);
                }
            }
        }

        //ボタン用の処理モードを設定する。
        paramMdl.setNtp040BtnCmd(StringUtil.toSingleCortationEscape(cmd));

        //ユーザテンプレートデータ取得
        NtpTemplateModel tmpMdl = new NtpTemplateModel();
        if (paramMdl.getNtp010SelectUsrSid() != null) {
            tmpMdl = biz.getUsrTemplate(con, Integer.valueOf(paramMdl.getNtp010SelectUsrSid()));
        }


        if (tmpMdl != null) {

            //項目設定
            String dspValueStr = StringUtilHtml.transToHTmlForTextArea(tmpMdl.getNttDetail());
            paramMdl.setNtp040DefaultValue(dspValueStr);
            paramMdl.setNtp040DefaultValue2(tmpMdl.getNttDetail());
            if (!copyFlg__) {
                paramMdl.setNtp040Value(tmpMdl.getNttDetail());
            }

            paramMdl.setNtp040AnkenUse(tmpMdl.getNttAnken());      //案件
            if (paramMdl.getAddressUseOk() == GSConstNippou.PLUGIN_NOT_USE) { //企業・顧客
                paramMdl.setNtp040CompanyUse(GSConstNippou.ITEM_NOT_USE);
            } else {
                paramMdl.setNtp040CompanyUse(tmpMdl.getNttComp());
            }
            paramMdl.setNtp040KtBriHhuUse(tmpMdl.getNttKatudo());  //活動分類・方法
            paramMdl.setNtp040MikomidoUse(tmpMdl.getNttMikomi());  //見込み度
            paramMdl.setNtp040NextActionUse(tmpMdl.getNttAction());     //次のアクション
            paramMdl.setNtp040TmpFileUse(tmpMdl.getNttTemp());     //添付ファイル

            if (tmpMdl.getNttAnken() == 0 && paramMdl.getNtp040CompanyUse() == 0) {
                paramMdl.setNtp040AnkenCompanyUse(GSConstNippou.ITEM_BOTH);
            } else if (tmpMdl.getNttAnken() == 0 && paramMdl.getNtp040CompanyUse() == 1) {
                paramMdl.setNtp040AnkenCompanyUse(GSConstNippou.ITEM_ANKEN_USE);
            } else if (tmpMdl.getNttAnken() == 1 && paramMdl.getNtp040CompanyUse() == 0) {
                paramMdl.setNtp040AnkenCompanyUse(GSConstNippou.ITEM_COMPANY_USE);
            } else {
                paramMdl.setNtp040AnkenCompanyUse(GSConstNippou.ITEM_BOTH_NOT_USE);
            }

            //ユーザ適用目標取得
            List<NtpTargetModel> trgList = null;
            if (paramMdl.getNtp010SelectUsrSid() != null) {
                trgList = biz.getUsrTmpTarget(
                        con, tmpMdl.getNttSid(), Integer.valueOf(paramMdl.getNtp010SelectUsrSid()));
            }


            //表示ユーザの表示月の目標取得
            if (trgList != null && !trgList.isEmpty()) {

                List<NtpPriTargetModel> priTrgList = null;
                NtpPriTargetModel priTrgMdl = null;
                NtpPriTargetDao priTrgDao = new NtpPriTargetDao(con);


                priTrgList = new ArrayList<NtpPriTargetModel>();

                List<Ntp040DspTargetModel> targetMdlList = new ArrayList<Ntp040DspTargetModel>();

                //5個で1セットにする
                int trgCnt = 0;
                int listCnt = 0;

                for (NtpTargetModel trgMdl : trgList) {

                    listCnt++;
                    trgCnt++;

                    if (trgMdl != null) {

                        //ユーザデータ取得
                        priTrgMdl = priTrgDao.select(
                                                    trgMdl.getNtgSid(),
                                                    Integer.valueOf(
                                                            paramMdl.getNtp010SelectUsrSid()),
                                                    dspDate.getYear(),
                                                    dspDate.getMonth());

                        if (priTrgMdl == null) {
                            //データがない場合は一番最近のデータの値を設定
                            priTrgMdl = priTrgDao.getLatelyData(
                              trgMdl.getNtgSid(), Integer.valueOf(
                                      paramMdl.getNtp010SelectUsrSid()));

                            if (priTrgMdl == null) {
                                //データがない場合はデフォルト値を設定
                                priTrgMdl = new NtpPriTargetModel();
                                //目標SID
                                priTrgMdl.setNtgSid(trgMdl.getNtgSid());
                                //デフォルト値
                                priTrgMdl.setNpgTarget(trgMdl.getNtgDef());
                                //実績
                                priTrgMdl.setNpgRecord(new Long(0));
                            } else {
                                //実績
                                priTrgMdl.setNpgRecord(new Long(0));
                            }
                        }

                        //名前
                        priTrgMdl.setNpgTargetName(trgMdl.getNtgName());
                        //単位
                        priTrgMdl.setNpgTargetUnit(trgMdl.getNtgUnit());

                        if (priTrgMdl.getNpgRecord() >= priTrgMdl.getNpgTarget()) {
                            //目標を達成している場合
                            priTrgMdl.setNpgTargetKbn(1);
                        } else {
                            priTrgMdl.setNpgTargetKbn(0);
                        }

                        priTrgList.add(priTrgMdl);
                    }

                    if (trgCnt == 4 || listCnt == trgList.size()) {



                        Ntp040DspTargetModel dspTrgMdl = new Ntp040DspTargetModel();
                        dspTrgMdl.setNttSid(tmpMdl.getNttSid());
                        dspTrgMdl.setUsrSid(Integer.valueOf(paramMdl.getNtp010SelectUsrSid()));
                        dspTrgMdl.setYear(dspDate.getYear());
                        dspTrgMdl.setMonth(dspDate.getMonth());

                        if (trgCnt < 4) {
                            for (int i = trgCnt; i < 4; i++) {
                                priTrgMdl = new NtpPriTargetModel();
                                priTrgList.add(priTrgMdl);
                            }
                        }

                        dspTrgMdl.setNtgList(priTrgList);
                        targetMdlList.add(dspTrgMdl);

                        priTrgList = new ArrayList<NtpPriTargetModel>();
                        trgCnt = 0;
                    }

                }

                paramMdl.setNtp040DspTargetMdlList(targetMdlList);
            }
        }

        //共通項目
        //カラーコメント
        NtpColMsgDao msgDao = new NtpColMsgDao(con);
        ArrayList<String> msgList = msgDao.selectMsg();
        paramMdl.setNtp040ColorMsgList(msgList);

        //見込み度基準
        NtpMikomidoMsgDao nmmDao = new NtpMikomidoMsgDao(con);
        ArrayList<NtpMikomidoMsgModel> nmmList = nmmDao.select();

        if (!nmmList.isEmpty()) {
            for (NtpMikomidoMsgModel nmmMdl : nmmList) {
                if (!StringUtil.isNullZeroStringSpace(nmmMdl.getNmmMsg())) {
                    nmmMdl.setNmmMsg(StringUtilHtml.returntoBR(
                            StringUtilHtml.transToHTmlForTextArea(nmmMdl.getNmmMsg())));
                    paramMdl.setNtp040MikomidoFlg(1);
                }
            }
        }

        paramMdl.setNtp040MikomidoMsgList(nmmList);

        //年コンボを作成
        paramMdl.setNtp040YearLavel(__getYearLabel(dspDate.getYear()));
        if (paramMdl.getNtp040YearLavel() != null) {
            if (paramMdl.getNtp040YearLavel().size() > 0) {
                String yearLavelStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040YearLavel()) {
                    yearLavelStr +=
                     "<input type=\"hidden\" class=\"yearclass\" name=\"yearvalue\" value=\""
                        + bean.getValue() + "_" + bean.getLabel() + "\">";
                }
                paramMdl.setNtp040YearLavelStr(yearLavelStr);
            }
        }

        //月コンボを作成
        paramMdl.setNtp040MonthLavel(getMonthLavel());
        if (paramMdl.getNtp040MonthLavel() != null) {
            if (paramMdl.getNtp040MonthLavel().size() > 0) {
                String monthLavelStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040MonthLavel()) {
                    monthLavelStr +=
                     "<input type=\"hidden\" class=\"monthclass\" name=\"monthvalue\" value=\""
                        + bean.getValue() + "_" + bean.getLabel() + "\">";
                }
                paramMdl.setNtp040MonthLavelStr(monthLavelStr);
            }
        }

        //日コンボを作成
        paramMdl.setNtp040DayLavel(getDayLavel());
        if (paramMdl.getNtp040DayLavel() != null) {
            if (paramMdl.getNtp040DayLavel().size() > 0) {
                String dayLavelStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040DayLavel()) {
                    dayLavelStr +=
                     "<input type=\"hidden\" class=\"dayclass\" name=\"dayvalue\" value=\""
                        + bean.getValue() + "_" + bean.getLabel() + "\">";
                }
                paramMdl.setNtp040DayLavelStr(dayLavelStr);
            }
        }

        //時コンボを作成
        paramMdl.setNtp040HourLavel(getHourLavel());
        if (paramMdl.getNtp040HourLavel() != null) {
            if (paramMdl.getNtp040HourLavel().size() > 0) {
                String hourLavelStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040HourLavel()) {
                    hourLavelStr +=
                     "<input type=\"hidden\" class=\"hourclass\" name=\"hourvalue\" value=\""
                        + bean.getValue() + "_" + bean.getLabel() + "\">";
                }
                paramMdl.setNtp040HourLavelStr(hourLavelStr);
            }
        }

        //分コンボを作成
        paramMdl.setNtp040MinuteLavel(getMinuteLavel(con));
        if (paramMdl.getNtp040MinuteLavel() != null) {
            if (paramMdl.getNtp040MinuteLavel().size() > 0) {
                String minLavelStr = "";
                for (LabelValueBean bean : paramMdl.getNtp040MinuteLavel()) {
                    minLavelStr +=
                     "<input type=\"hidden\" class=\"minclass\" name=\"minvalue\" value=\""
                        + bean.getValue() + "_" + bean.getLabel() + "\">";
                }
                paramMdl.setNtp040MinuteLavelStr(minLavelStr);
            }
        }


        paramMdl.setNtp040FrYear(
                NullDefault.getString(paramMdl.getNtp040FrYear(),
                        String.valueOf(uDate.getYear())));
        paramMdl.setNtp040FrMonth(
                NullDefault.getString(paramMdl.getNtp040FrMonth(),
                        String.valueOf(uDate.getMonth())));
        paramMdl.setNtp040FrDay(
                NullDefault.getString(paramMdl.getNtp040FrDay(),
                        String.valueOf(uDate.getIntDay())));
        paramMdl.setNtp040ToYear(
                NullDefault.getString(paramMdl.getNtp040ToYear(),
                        String.valueOf(uDate.getYear())));
        paramMdl.setNtp040ToMonth(
                NullDefault.getString(paramMdl.getNtp040ToMonth(),
                        String.valueOf(uDate.getMonth())));
        paramMdl.setNtp040ToDay(
                NullDefault.getString(paramMdl.getNtp040ToDay(),
                        String.valueOf(uDate.getIntDay())));

        paramMdl.setNtp040NxtActYear(
                NullDefault.getString(paramMdl.getNtp040NxtActYear(),
                        String.valueOf(uDate.getYear())));
        paramMdl.setNtp040NxtActMonth(
                NullDefault.getString(paramMdl.getNtp040NxtActMonth(),
                        String.valueOf(uDate.getMonth())));
        paramMdl.setNtp040NxtActDay(
                NullDefault.getString(paramMdl.getNtp040NxtActDay(),
                        String.valueOf(uDate.getIntDay())));


        //時間
        UDate frDate = null;
        UDate toDate = null;
        if (confMdl != null) {
            frDate = confMdl.getNprIniFrDate();
            toDate = confMdl.getNprIniToDate();
        } else {
            frDate = new UDate();
            toDate = new UDate();
            frDate.setHour(GSConstNippou.DF_FROM_HOUR);
            frDate.setMinute(GSConstNippou.DF_FROM_MINUTES);
            toDate.setHour(GSConstNippou.DF_TO_HOUR);
            toDate.setMinute(GSConstNippou.DF_TO_MINUTES);
        }
        paramMdl.setNtp040FrHour(
                NullDefault.getStringZeroLength(paramMdl.getNtp040FrHour(),
                String.valueOf(frDate.getIntHour())));
        paramMdl.setNtp040FrMin(
                NullDefault.getStringZeroLength(paramMdl.getNtp040FrMin(),
                        String.valueOf(frDate.getIntMinute())));
        paramMdl.setNtp040ToHour(
                NullDefault.getStringZeroLength(paramMdl.getNtp040ToHour(),
                        String.valueOf(toDate.getIntHour())));
        paramMdl.setNtp040ToMin(
                NullDefault.getStringZeroLength(paramMdl.getNtp040ToMin(),
                        String.valueOf(toDate.getIntMinute())));
        CommonBiz commonBiz = new CommonBiz();
        boolean adminUser = commonBiz.isPluginAdmin(
                con, usModel, GSConstNippou.PLUGIN_ID_NIPPOU);
        if (adminUser) {
            paramMdl.setAdminKbn(GSConst.USER_ADMIN);
        } else {
            paramMdl.setAdminKbn(GSConst.USER_NOT_ADMIN);
        }

        //検索画面パラメータ
        if (paramMdl.getNtp100SvSearchTarget() != null) {
            if (paramMdl.getNtp100SvSearchTarget().length > 0) {
                String svSearchTargetStr = "";
                for (String str : paramMdl.getNtp100SvSearchTarget()) {
                    svSearchTargetStr +=
                      "<input type=\"hidden\" name=\"ntp100SvSearchTarget\" value=\""
                      + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100SvSearchTarget(svSearchTargetStr);
            }
        }
        if (paramMdl.getNtp100SearchTarget() != null) {
            if (paramMdl.getNtp100SearchTarget().length > 0) {
                String searchTargetStr = "";
                for (String str : paramMdl.getNtp100SearchTarget()) {
                    searchTargetStr +=
                        "<input type=\"hidden\" name=\"ntp100SearchTarget\" value=\""
                        + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100SearchTarget(searchTargetStr);
            }
        }
        if (paramMdl.getNtp100SvBgcolor() != null) {
            if (paramMdl.getNtp100SvBgcolor().length > 0) {
                String svBgcolor = "";
                for (String str : paramMdl.getNtp100SvBgcolor()) {
                    svBgcolor +=
                        "<input type=\"hidden\" name=\"ntp100SvBgcolor\" value=\""
                        + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100SvBgcolor(svBgcolor);
            }
        }
        if (paramMdl.getNtp100Bgcolor() != null) {
            if (paramMdl.getNtp100Bgcolor().length > 0) {
                String bgcolor = "";
                for (String str : paramMdl.getNtp100Bgcolor()) {
                    bgcolor +=
                        "<input type=\"hidden\" name=\"ntp100Bgcolor\" value=\""
                        + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100Bgcolor(bgcolor);
            }
        }
        if (paramMdl.getNtp100SvMikomido() != null) {
            if (paramMdl.getNtp100SvMikomido().length > 0) {
                String svMikomido = "";
                for (String str : paramMdl.getNtp100SvMikomido()) {
                    svMikomido +=
                        "<input type=\"hidden\" name=\"ntp100SvMikomido\" value=\""
                        + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100SvMikomido(svMikomido);
            }
        }
        if (paramMdl.getNtp100Mikomido() != null) {
            if (paramMdl.getNtp100Mikomido().length > 0) {
                String mikomido = "";
                for (String str : paramMdl.getNtp100Mikomido()) {
                    mikomido +=
                        "<input type=\"hidden\" name=\"ntp100Mikomido\" value=\""
                        + StringUtilHtml.transToHTmlPlusAmparsant(str) + "\">";
                }
                paramMdl.setNtp040100Mikomido(mikomido);
            }
        }


        //初期表示完了
        paramMdl.setNtp040InitFlg(String.valueOf(GSConstNippou.NOT_INIT_FLG));

        //ユーザ情報取得
        CmnUsrmInfModel model = new CmnUsrmInfModel();
        model = getUsrInfo(String.valueOf(sessionUsrSid));
        if (model != null) {
            paramMdl.setNtp040UsrInfMdl(model);
        }


        //戻るボタン押下時設定パラメータ
        paramMdl.setNtp010SelectUsrAreaSid(paramMdl.getNtp010SelectUsrSid());
        paramMdl.setNtp100SelectNtpAreaSid(NullDefault.getString(paramMdl.getNtp010NipSid(), "-1"));

        return paramMdl;
    }

    /**
     * <br>[機  能] 日報確認情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSids 日報SID
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void setCheck(List<Integer> ntpSids, int userSid) throws Exception {
        boolean commitFlg = false;
        con__.setAutoCommit(false);
        try {

            NtpCheckDao chDao = new NtpCheckDao(con__);
            //確認情報を削除
            chDao.delete(ntpSids, userSid);

            //確認情報を登録
            for (int ntpSid : ntpSids) {
                chDao.insert(ntpSid, userSid);
            }

            con__.commit();
            commitFlg = true;
        } catch (Exception e) {
            log__.error("目標の登録・更新に失敗しました" + e);
            throw e;
        } finally {
            if (!commitFlg) {
                con__.rollback();
            }
        }
    }

    /**
     * <br>[機  能] 日報確認情報を登録する
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSids 日報SID
     * @param userSid ユーザSID
     * @throws Exception 実行例外
     */
    public void resetCheck(List<Integer> ntpSids, int userSid) throws Exception {
        boolean commitFlg = false;
        con__.setAutoCommit(false);
        try {

            NtpCheckDao chDao = new NtpCheckDao(con__);
            //指定ユーザ以外の確認情報をリセット
            chDao.resetData(ntpSids, userSid);

            con__.commit();
            commitFlg = true;
        } catch (Exception e) {
            log__.error("目標の登録・更新に失敗しました" + e);
            throw e;
        } finally {
            if (!commitFlg) {
                con__.rollback();
            }
        }
    }

    /**
     * <br>[機  能] DBから案件情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param nanSid 案件SID
     * @param dataMdl 日報データ
     * @throws SQLException SQL実行時例外
     */
    public void _getAnkenData(Connection con,
                              int nanSid,
                              Ntp040DataModel dataMdl) throws SQLException {

        //案件情報
        NtpAnkenDao ankenDao = new NtpAnkenDao(con);
        NtpAnkenModel ankenModel = ankenDao.select(nanSid);

        if (ankenModel != null) {
            dataMdl.setAnkenSid(nanSid);
            dataMdl.setAnkenCode(ankenModel.getNanCode());
            dataMdl.setAnkenName(ankenModel.getNanName());
        }

    }

    /**
     * <br>[機  能] DBから会社情報を取得し、パラメータとして設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param acoSid 会社SID
     * @param abaSid 拠点SID
     * @param dataMdl 日報データ
     * @throws SQLException SQL実行時例外
     */
    public void _readCompanyData(Connection con,
                                 int acoSid,
                                 int abaSid,
                                 Ntp040DataModel dataMdl) throws SQLException {

        //会社情報
        AdrCompanyDao companyDao = new AdrCompanyDao(con);
        AdrCompanyModel companyModel = companyDao.select(acoSid);

        if (companyModel != null) {
            dataMdl.setCompanySid(acoSid);
            dataMdl.setCompanyCode(companyModel.getAcoCode());
            dataMdl.setCompanyName(companyModel.getAcoName());
        }

        //会社拠点情報
        AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
        AdrCompanyBaseModel companyBaseMdl = new AdrCompanyBaseModel();
        companyBaseMdl = companyBaseDao.select(abaSid);
        if (companyBaseMdl != null) {
            dataMdl.setCompanyBaseSid(abaSid);
            dataMdl.setCompanyBaseName(companyBaseMdl.getAbaName());
        }
    }


    /**
     * <br>[機  能] ユーザSIDとユーザ区分からユーザ氏名を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param usrKbn ユーザ区分
     * @param con コネクション
     * @return String ユーザ氏名
     * @throws SQLException SQL実行時例外
     */
    public String getUsrName(int usrSid, int usrKbn, Connection con)
    throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        String ret = "";
        if (usrKbn == GSConstNippou.USER_KBN_GROUP) {
            //グループ
            String textGroup = gsMsg.getMessage("cmn.group");
            if (usrSid == GSConstNippou.NIPPOU_GROUP) {
                ret = textGroup;
            } else {
                GroupDao grpDao = new GroupDao(con);
                ret = grpDao.getGroup(usrSid).getGrpName();
            }

        } else {
            UserSearchDao uDao = new UserSearchDao(con);
            CmnUsrmInfModel uMdl = uDao.getUserInfoJtkb(usrSid, GSConstUser.USER_JTKBN_ACTIVE);
            ret = uMdl.getUsiSei() + " " + uMdl.getUsiMei();
            uMdl.getBinSid();
        }
        return ret;
    }

    /**
     * <br>[機  能] ユーザ情報取得
     * <br>[解  説]
     * <br>[備  考]
     * @param usrSid ユーザSID
     * @param con コネクション
     * @return uMdl ユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public CmnUsrmInfModel getUsrInf(int usrSid, Connection con)
    throws SQLException {

        UserSearchDao uDao = new UserSearchDao(con);
        CmnUsrmInfModel uMdl = uDao.getUserInfoJtkb(usrSid, GSConstUser.USER_JTKBN_ACTIVE);

        return uMdl;
    }

    /**
     * <br>[機  能] 日報SIDから日報情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSid 日報SID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @param paramMdl Ntp040ParamModel
     * @return NippouSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<NippouSearchModel> getNtpData(
            int ntpSid,
            NtpAdmConfModel adminConf,
            Connection con,
            Ntp040ParamModel paramMdl)
    throws SQLException {

        ArrayList<NippouSearchModel> ntpMdlList = new ArrayList<NippouSearchModel>();

        try {
            NippouSearchDao ntpDao = new NippouSearchDao(con);
            ntpMdlList =
                ntpDao.getNippouDataAll(ntpSid, Integer.parseInt(paramMdl.getNtp010SelectUsrSid()));


        } catch (SQLException e) {
            log__.error("日報情報の取得に失敗" + e);
            throw e;
        }

        return ntpMdlList;
    }

    /**
     * <br>[機  能] 日報SIDから日報情報を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSid 日報SID
     * @param adminConf 管理者設定
     * @param con コネクション
     * @param paramMdl Ntp040ParamModel
     * @return NippouSearchModel
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<NippouSearchModel> getNtpDataSingle(
            int ntpSid,
            NtpAdmConfModel adminConf,
            Connection con,
            Ntp040ParamModel paramMdl)
    throws SQLException {

        ArrayList<NippouSearchModel> ntpMdlList = new ArrayList<NippouSearchModel>();

        try {
            NippouSearchModel nsmMdl = new NippouSearchModel();
            NippouSearchDao ntpDao = new NippouSearchDao(con);
            nsmMdl =
                ntpDao.getNippouDataSingle(
                        ntpSid, Integer.parseInt(paramMdl.getNtp010SelectUsrSid()));

            if (nsmMdl != null) {
                ntpMdlList.add(nsmMdl);
            }

        } catch (SQLException e) {
            log__.error("日報情報の取得に失敗" + e);
            throw e;
        }

        return ntpMdlList;
    }

    /**
     * <br>[機  能] 選択している日報の前後の日付の日報を取得
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpDate 日報日付
     * @param con コネクション
     * @param paramMdl Ntp040ParamModel
     * @param kbn 0:前  1:次
     * @return NippouSearchModel
     * @throws SQLException SQL実行時例外
     */
    public NippouSearchModel getNtpDataPrevNext(
            UDate ntpDate,
            Ntp040ParamModel paramMdl,
            Connection con,
            int kbn)
    throws SQLException {

        UDate thisDate = ntpDate.cloneUDate();

        if (kbn == 0) {
            thisDate.addDay(-1);
        } else if (kbn == 1) {
            thisDate.addDay(1);
        } else {
            thisDate = new UDate();
        }

        thisDate.setZeroHhMmSs();

        NippouSearchModel nsmMdl = null;
        try {

            NippouSearchDao ntpDao = new NippouSearchDao(con);
            nsmMdl =
                ntpDao.getPrevNextNippouSid(
                  thisDate, Integer.parseInt(paramMdl.getNtp010SelectUsrSid()));

        } catch (SQLException e) {
            log__.error("日報情報の取得に失敗" + e);
            throw e;
        }

        return nsmMdl;
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

        Ntp170Dao bunruiDao = new Ntp170Dao(con);
        List<NtpKtbunruiModel> ret = null;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        Map<Integer, String> ktMap = new HashMap<Integer, String>();

        ret = bunruiDao.getKtBunruiList();

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

        Ntp180Dao houhouDao = new Ntp180Dao(con);
        List<NtpKthouhouModel> ret = null;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        Map<Integer, String> ktMap = new HashMap<Integer, String>();

        ret = houhouDao.getKthouhouList();

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
     * <br>[機  能] 見込み度を取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param mikomi 見込み度
     * @return 活動方法
     */
    public String getMikomido(int mikomi) {

        String mikomido = "10";

        switch (mikomi) {
        case 0:
            mikomido = "10";
            break;
        case 1:
            mikomido = "30";
            break;
        case 2:
            mikomido = "50";
            break;
        case 3:
            mikomido = "70";
            break;
        case 4:
            mikomido = "100";
            break;
        default:
            mikomido = "10";
        }
        return mikomido;
    }

    /**
     * 年コンボを作成する
     * @param year 基準年
     * @return List (in LabelValueBean)  コンボ
     */
    private ArrayList < LabelValueBean > __getYearLabel(int year) {

        int sysYear = year;
        int start = sysYear - 5;
        int end = sysYear + 5;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        ArrayList < LabelValueBean > labelList = new ArrayList < LabelValueBean >();

        for (int value = start; value <= end; value++) {
            labelList.add(
              new LabelValueBean(
                 gsMsg.getMessage(
                   "cmn.year", new String[] {String.valueOf(value)}), String.valueOf(value)));
        }
        return labelList;
    }

    /**
     * <br>[機  能] 月コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    public ArrayList<LabelValueBean> getMonthLavel() {
        int month = 1;
        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ 月 **/
        String strMonth = gsMsg.getMessage("cmn.month");
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month + strMonth, String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 日コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    public ArrayList<LabelValueBean> getDayLavel() {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        /** メッセージ 月 **/
        String strDay = gsMsg.getMessage("cmn.day");
        int day = 1;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + strDay, String.valueOf(day)));
            day++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 時コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @return ArrayList (in LabelValueBean)  時コンボ
     */
    public ArrayList<LabelValueBean> getHourLavel() {
        int hour = 0;
        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        ArrayList<String> hourList = new ArrayList<String>();

        for (int i = 0; i < 34; i++) {
            labelList.add(
                    new LabelValueBean(String.valueOf(hour), String.valueOf(hour)));
            hourList.add(String.valueOf(hour));
            hour++;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 分コンボを生成します
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @return ArrayList (in LabelValueBean)  分コンボ
     * @throws SQLException SQL実行時例外
     */
    public ArrayList<LabelValueBean> getMinuteLavel(Connection con) throws SQLException {
        NtpCommonBiz cmnBiz = new NtpCommonBiz(con__, reqMdl__);
        int hourDivCount = cmnBiz.getDayNippouHourMemoriCount(con);
        int min = 0;

        ArrayList<LabelValueBean> labelList = new ArrayList<LabelValueBean>();
        int hourMemCount = 60 / hourDivCount;
        for (int i = 0; i < hourDivCount; i++) {
            labelList.add(
                    new LabelValueBean(
                            StringUtil.toDecFormat(min, "00"), String.valueOf(min)));
            min = min + hourMemCount;
        }
        return labelList;
    }

    /**
     * <br>[機  能] 日報を新規登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param param 日報データパラメータ

     * @param paramMdl アクションフォーム
     * @param userSid 登録者SID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param tempDir テンポラリディレクリ
     * @param reqMdl リクエストモデル
     * @return List<Integer> 登録日報SID
     * @throws Exception SQL実行時例外
     */
    public int insertNippouDate(
            Ntp040Param param,
            RequestModel reqMdl,
            Ntp040ParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg,
            String tempDir) throws Exception {

        NtpDataModel ntpMdl = null;
        NtpCommonBiz cmnBiz = new NtpCommonBiz(con__, reqMdl__);

        //登録モデルを作成
        ntpMdl = new NtpDataModel();
        UDate frDate = new UDate();
        UDate toDate = new UDate();
        UDate actionDate = new UDate();
        UDate now = new UDate();

        int frYear = param.getNtpYear();
        int frMonth = param.getNtpMonth();
        int frDay = param.getNtpDay();


        ntpMdl.setNipActDateKbn(param.getActDateKbn());
        if (param.getActDateKbn() != 0) {
            int actionYear = param.getActionYear();
            int actionMonth = param.getActionMonth();
            int actionDay = param.getActionDay();
            actionDate.setDate(actionYear, actionMonth, actionDay);
            actionDate.setZeroHhMmSs();
            ntpMdl.setNipActionDate(actionDate.cloneUDate());
        }


        int frHour = GSConstNippou.DAY_START_HOUR;
        int frMin = GSConstNippou.DAY_START_MINUTES;
        int toHour = GSConstNippou.DAY_END_HOUR;
        int toMin = GSConstNippou.DAY_END_MINUTES;

        frHour = param.getFrHour();
        frMin = param.getFrMin();
        toHour = param.getToHour();
        toMin = param.getToMin();

        frDate.setDate(frYear, frMonth, frDay);
        frDate.setZeroHhMmSs();
        ntpMdl.setNipDate(frDate.cloneUDate());

        if (frHour != -1 && frMin != -1) {
            frDate.setHour(frHour);
            frDate.setMinute(frMin);
            frDate.setSecond(GSConstNippou.DAY_START_SECOND);
            frDate.setMilliSecond(GSConstNippou.DAY_START_MILLISECOND);
        }

        toDate.setDate(frYear, frMonth, frDay);
        if (toHour != -1 && toMin != -1) {
            toDate.setHour(toHour);
            toDate.setMinute(toMin);
            toDate.setSecond(GSConstNippou.DAY_START_SECOND);
            toDate.setMilliSecond(GSConstNippou.DAY_START_MILLISECOND);
        }

        //時間
        ntpMdl.setNipFrTime(frDate);
        ntpMdl.setNipToTime(toDate);

        //案件
        ntpMdl.setNanSid(param.getAnkenSid());

        //会社SID
        ntpMdl.setAcoSid(param.getCompanySid());

        //会社拠点SID
        if (param.getCompanyBaseSid() != -1) {
            ntpMdl.setAbaSid(param.getCompanyBaseSid());
        }

        //活動分類
        ntpMdl.setMkbSid(param.getKtbunruiSid());

        //活動方法
        ntpMdl.setMkhSid(param.getKthouhouSid());

        //見込み度
        ntpMdl.setNipMikomi(param.getMikomido());

        ntpMdl.setNipTitleClo(param.getBgcolor());
        ntpMdl.setNipTitle(param.getTitle());
        ntpMdl.setNipDetail(param.getValueStr());
        ntpMdl.setNipAction(param.getActionStr());
        ntpMdl.setNipSyokan("");
        ntpMdl.setNipPublic(GSConstNippou.DSP_PUBLIC);

        ntpMdl.setNipAuid(userSid);
        ntpMdl.setNipAdate(now);
        ntpMdl.setNipEuid(userSid);
        ntpMdl.setNipEdate(now);
        //編集区分
        ntpMdl.setNipEdit(GSConstNippou.EDIT_CONF_NONE);
        int ntpSid = -1;


        //添付ファイルを登録
        CommonBiz biz = new CommonBiz();
        List<String> binList =
            biz.insertBinInfo(con__, tempDir, appRootPath, cntCon__, userSid, now);


        //SID採番
        ntpSid = (int) cntCon__.getSaibanNumber(GSConstNippou.SBNSID_NIPPOU,
                GSConstNippou.SBNSID_SUB_NIPPOU, userSid);
        ntpMdl.setNipSid(ntpSid);
        ntpMdl.setUsrSid(param.getSelectUsrSid());

        NtpDataDao ntpDao = new NtpDataDao(con__);

        //登録
        ntpDao.insert(ntpMdl);

        NtpBinDao sbinDao = new NtpBinDao(con__);
        //日報添付情報を登録
        sbinDao.insertNtpBin(ntpMdl, binList);

        //案件が選択されている場合は案件の見込み度を更新
        if (ntpMdl.getNanSid() > 0 && ntpMdl.getNipMikomi() >= 0) {
            //ユーザテンプレートデータ取得
            NtpTemplateModel tmpMdl = new NtpTemplateModel();
            if (param.getSelectUsrSid() > 0) {
                NtpCommonBiz ntpBiz = new NtpCommonBiz(con__, reqMdl__);
                tmpMdl =
                    ntpBiz.getUsrTemplate(con__, param.getSelectUsrSid());
            }
            NtpAnkenDao ankenDao = new NtpAnkenDao(con__);
            if (tmpMdl != null) {
                if (tmpMdl.getNttMikomi() == GSConstNippou.ITEM_USE) {
                    ankenDao.updateMikomido(ntpMdl.getNanSid(), ntpMdl.getNipMikomi());
                }
            } else {
                ankenDao.updateMikomido(ntpMdl.getNanSid(), ntpMdl.getNipMikomi());
            }
        }

        //URL取得
        String url = __createNippouUrlDefo(GSConstNippou.CMD_EDIT,
                                         String.valueOf(ntpSid),
                                         String.valueOf(userSid),
                                         ntpMdl, paramMdl);

        //ショートメール通知
        cmnBiz.sendSmail(
          con__, cntCon__, ntpMdl, appRootPath, plconf, smailPluginUseFlg, reqMdl, url);


        return ntpSid;
    }

    /**
     * <br>[機  能] 日報を更新します
     * <br>[解  説]
     * <br>[備  考]
     * @param param 日報データパラメータ

     * @param paramMdl アクションフォーム
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションRoot
     * @param plconf プラグイン設定
     * @param smailPluginUseFlg ショートメールプラグイン有効フラグ
     * @param tempDir テンポラリディレクリ
     * @param changeNtpSid 変更日報SID
     * @return int 更新日報SID
     * @throws Exception SQL実行時例外
     */
    public int updateNippouDate(
            Ntp040Param param,
            Ntp040ParamModel paramMdl,
            int userSid,
            String appRootPath,
            PluginConfig plconf,
            boolean smailPluginUseFlg,
            String tempDir,
            String changeNtpSid) throws Exception {

        CommonBiz cmnBiz = new CommonBiz();

        String ntpSid = changeNtpSid;

        if (Integer.valueOf(ntpSid) != -1) {
            NtpDataModel ntpMdl = new NtpDataModel();
            UDate now = new UDate();
            UDate frDate = new UDate();
            UDate actionDate = new UDate();


            ntpMdl.setNipActDateKbn(param.getActDateKbn());
            if (param.getActDateKbn() != 0) {
                int actionYear = param.getActionYear();
                int actionMonth = param.getActionMonth();
                int actionDay = param.getActionDay();
                actionDate.setDate(actionYear, actionMonth, actionDay);
                actionDate.setZeroHhMmSs();
                ntpMdl.setNipActionDate(actionDate.cloneUDate());
            }

            frDate.setDate(param.getNtpYear(), param.getNtpMonth(), param.getNtpDay()
                    );
            frDate.setZeroHhMmSs();
            ntpMdl.setNipDate(frDate.cloneUDate());

            int frHour = GSConstNippou.DAY_START_HOUR;
            int frMin = GSConstNippou.DAY_START_MINUTES;
            int toHour = GSConstNippou.DAY_END_HOUR;
            int toMin = GSConstNippou.DAY_END_MINUTES;

            frHour = param.getFrHour();
            frMin = param.getFrMin();
            toHour = param.getToHour();
            toMin = param.getToMin();


            if (frHour != -1 && frMin != -1) {
                frDate.setHour(frHour);
                frDate.setMinute(frMin);
                frDate.setSecond(GSConstNippou.DAY_START_SECOND);
                frDate.setMilliSecond(GSConstNippou.DAY_START_MILLISECOND);

            }

            UDate toDate = new UDate();
            toDate.setDate(param.getNtpYear(),
                           param.getNtpMonth(),
                           param.getNtpDay());

            if (toHour != -1 && toMin != -1) {
                toDate.setHour(toHour);
                toDate.setMinute(toMin);
                toDate.setSecond(GSConstNippou.DAY_START_SECOND);
                toDate.setMilliSecond(GSConstNippou.DAY_START_MILLISECOND);
            }

            ntpMdl.setNipSid(Integer.parseInt(ntpSid));

            //案件
            ntpMdl.setNanSid(param.getAnkenSid());

            //会社SID
            ntpMdl.setAcoSid(param.getCompanySid());

            //会社拠点SID
            if (param.getCompanyBaseSid() != -1) {
                ntpMdl.setAbaSid(param.getCompanyBaseSid());
            }

            //活動分類
            ntpMdl.setMkbSid(param.getKtbunruiSid());

            //活動方法
            ntpMdl.setMkhSid(param.getKthouhouSid());

            //見込み度
            ntpMdl.setNipMikomi(param.getMikomido());

            ntpMdl.setNipFrTime(frDate);
            ntpMdl.setNipToTime(toDate);
            ntpMdl.setNipTitleClo(param.getBgcolor());
            ntpMdl.setNipTitle(param.getTitle());
            ntpMdl.setNipDetail(param.getValueStr());
            ntpMdl.setNipAction(param.getActionStr());
            ntpMdl.setNipSyokan("");
            ntpMdl.setNipPublic(
                    NullDefault.getInt(paramMdl.getNtp040Public(), GSConstNippou.DSP_PUBLIC));

            ntpMdl.setNipAuid(userSid);
            ntpMdl.setNipAdate(now);
            ntpMdl.setNipEuid(userSid);
            ntpMdl.setNipEdate(now);

            //編集区分
            ntpMdl.setNipEdit(
                    NullDefault.getInt(paramMdl.getNtp040Edit(), GSConstNippou.EDIT_CONF_NONE));


            NtpDataDao ntpDao = new NtpDataDao(con__);

            ntpMdl.setUsrSid(param.getSelectUsrSid());

            //登録
            ntpDao.update(ntpMdl);


            //ユーザテンプレートデータ取得
            NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
            NtpTemplateModel tmpMdl = null;
            tmpMdl = biz.getUsrTemplate(
                    con__, ntpMdl.getUsrSid());

            if (tmpMdl == null) {
                tmpMdl = new NtpTemplateModel();
                tmpMdl.setNttTemp(GSConstNippou.ITEM_USE);
            }

            //添付ファイル情報取得
            List<String> binList = new ArrayList<String>();
            if (tmpMdl.getNttTemp() == GSConstNippou.ITEM_USE) {
                //バイナリ情報を登録
                binList =
                    cmnBiz.insertBinInfo(con__, tempDir, appRootPath, cntCon__, userSid, now);
            }

            NtpBinDao sbinDao = new NtpBinDao(con__);
            //日報添付情報を登録
            sbinDao.deleteNtpBin(Integer.valueOf(ntpSid));
            sbinDao.insertNtpBin(ntpMdl, binList);

            //案件が選択されている場合は案件の見込み度を更新
            if (ntpMdl.getNanSid() > 0 && ntpMdl.getNipMikomi() >= 0) {
                //ユーザテンプレートデータ取得
                NtpAnkenDao ankenDao = new NtpAnkenDao(con__);
                if (tmpMdl.getNttMikomi() == GSConstNippou.ITEM_USE) {
                    ankenDao.updateMikomido(ntpMdl.getNanSid(), ntpMdl.getNipMikomi());
                }
            }

        }

        return Integer.valueOf(ntpSid);
    }

    /**
     * <br>[機  能] 日報を削除(物理削除)します
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSid 日報SID
     * @param con コネクション
     * @return 削除レコード件数
     * @throws SQLException SQL実行時例外
     */
    public int deleteNippou(int ntpSid, Connection con) throws SQLException {

        int cnt = 0;

        //添付ファイル情報削除
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
        List<String> ntpSids = new ArrayList<String>();
        ntpSids.add(String.valueOf(ntpSid));
        biz.deleteNippouFile(con, ntpSids);
        //日報コメント削除
        NtpCommentDao cmtDao = new  NtpCommentDao(con);
        cmtDao.deleteNtpData(ntpSid);
        //いいね削除
        NtpGoodDao goodDao = new NtpGoodDao(con);
        goodDao.delete(ntpSid);
        //確認削除
        NtpCheckDao checkDao = new NtpCheckDao(con);
        checkDao.delete(ntpSid);
        //日報削除
        NtpDataDao ntpDao = new NtpDataDao(con);
        cnt = ntpDao.delete(ntpSid);
        return cnt;
    }

    /**
     * <br>[機  能] json形式で日報データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param ntpSid 日報SID
     * @return jsonデータ
     * @throws Exception 実行時例外
     */
    public JSONObject getNtpJsonData(Connection con,
                                     String ntpSid) throws Exception {

        GsMessage gsMsg = new GsMessage(reqMdl__);
        NippouSearchDao dao = new NippouSearchDao(con);

        JSONObject jsonObject = null;

        //日報データ取得
        NippouSearchModel ntpMdl = null;
        ntpMdl = dao.getNippouData(Integer.parseInt(ntpSid));

        if (ntpMdl != null) {

            //日報データリスト
            Ntp040DataModel dataMdl = new Ntp040DataModel();

            if (String.valueOf(ntpMdl.getNipSid()).equals(ntpSid)) {
                dataMdl.setNtp040SelectFlg(1);
            }

            dataMdl.setNtp040NtpSid(ntpMdl.getNipSid());

            CmnUsrmInfModel uMdl = null;

            UserSearchDao uDao = new UserSearchDao(con);
            CmnUsrmDao cuDao = new CmnUsrmDao(con);
            //登録者
            uMdl = uDao.getUserInfoJtkb(ntpMdl.getNipAuid(), -1);
            if (uMdl != null) {
                ntpMdl.setNipAuidSei(uMdl.getUsiSei());
                ntpMdl.setNipAuidMei(uMdl.getUsiMei());
                ntpMdl.setNipAuidJkbn(cuDao.getUserJkbn(ntpMdl.getNipAuid()));
            }
            dataMdl.setNtp040NtpUsiSei(uMdl.getUsiSei());
            dataMdl.setNtp040NtpUsiMei(uMdl.getUsiMei());
            dataMdl.setNtp040NtpUsiId(uMdl.getUsrSid());
            dataMdl.setNtp040NtpAddUsrName(
                    ntpMdl.getNipAuidSei() + " " + ntpMdl.getNipAuidMei());

            //登録日時
            String textAddDate = gsMsg.getMessage("schedule.src.84");

            dataMdl.setNtp040NtpDate(textAddDate + " : "
                    + UDateUtil.getSlashYYMD(ntpMdl.getNipAdate())
                    + " "
                    + UDateUtil.getSeparateHM(ntpMdl.getNipAdate()));

            UDate ntpDate = ntpMdl.getNipDate();
            UDate frDate  = ntpMdl.getNipFrTime();
            UDate toDate  = ntpMdl.getNipToTime();
            //開始年月日
            dataMdl.setNtpYear(ntpDate.getYear());
            dataMdl.setNtpMonth(ntpDate.getMonth());
            dataMdl.setNtpDay(ntpDate.getIntDay());


            //活動分類
            getKtbunruiLabelList(con);
            dataMdl.setKtbunruiSid(ntpMdl.getMkbSid());
            dataMdl.setNtp040DspKtbunrui(getKtbunrui(ntpMdl.getMkbSid()));

            //活動方法
            getKthouhouLabelList(con);
            dataMdl.setKthouhouSid(ntpMdl.getMkhSid());
            dataMdl.setNtp040DspKthouhou(getKthouhou(ntpMdl.getMkhSid()));

            //見込み度
            dataMdl.setMikomido(ntpMdl.getNipMikomi());
            dataMdl.setNtp040DspMikomido(getMikomido(ntpMdl.getNipMikomi()));

            if (frDate.equalsDate(ntpMdl.getNipDate())) {
                dataMdl.setFrHour(frDate.getIntHour());
                dataMdl.setNtp040DspFrHour(
                        StringUtil.toDecFormat(frDate.getIntHour(), "00"));
            } else {
                dataMdl.setFrHour(frDate.getIntHour() + 24);
                dataMdl.setNtp040DspFrHour(
                        StringUtil.toDecFormat(frDate.getIntHour() + 24, "00"));
            }

            dataMdl.setFrMin(frDate.getIntMinute());
            dataMdl.setNtp040DspFrMinute(
                    StringUtil.toDecFormat(frDate.getIntMinute(), "00"));


            if (frDate.equalsDate(toDate)
                    && ntpMdl.getNipDate().equalsDate(frDate)) {
                dataMdl.setToHour(toDate.getIntHour());
                dataMdl.setNtp040DspToHour(
                        StringUtil.toDecFormat(toDate.getIntHour(), "00"));
            } else {
                dataMdl.setToHour(toDate.getIntHour() + 24);
                dataMdl.setNtp040DspToHour(
                        StringUtil.toDecFormat(toDate.getIntHour() + 24, "00"));
            }

            dataMdl.setToMin(toDate.getIntMinute());
            dataMdl.setNtp040DspToMinute(
                    StringUtil.toDecFormat(toDate.getIntMinute(), "00"));


            //背景
            int iniBgcolor = GSConstNippou.DF_BG_COLOR;
            if (ntpMdl.getNipTitleClo() > GSConstNippou.DF_BG_COLOR) {
                iniBgcolor = ntpMdl.getNipTitleClo();
            }
            dataMdl.setBgcolor(iniBgcolor);

            //タイトル
            dataMdl.setTitle(ntpMdl.getNipTitle());

            //詳細
            dataMdl.setValueStr(ntpMdl.getNipDetail());
            String dspValueStr = StringUtil.transToLink(
                    StringUtilHtml.transToHTmlForTextArea(ntpMdl.getNipDetail()),
                    StringUtil.OTHER_WIN, true);
            dataMdl.setNtp040DspValueStr(StringUtilHtml.returntoBR(dspValueStr));
            dataMdl.setValueStr(ntpMdl.getNipDetail());


            //次のアクション日付
            dataMdl.setActDateKbn(ntpMdl.getNipActDateKbn());
            if (ntpMdl.getNipActDateKbn() != 0) {
                UDate ntpActDate = ntpMdl.getNipActionDate();
                dataMdl.setActionYear(ntpActDate.getYear());
                dataMdl.setActionMonth(ntpActDate.getMonth());
                dataMdl.setActionDay(ntpActDate.getIntDay());
            }

            //次のアクション
            dataMdl.setActionStr(ntpMdl.getNipAction());
            String dspActionStr = StringUtilHtml.transToHTmlForTextArea(ntpMdl.getNipAction());
            dataMdl.setNtp040DspActionStr(StringUtilHtml.returntoBR(dspActionStr));

            //案件情報取得
            _getAnkenData(con, ntpMdl.getNanSid(), dataMdl);

            //会社情報、アドレス帳情報を設定
            _readCompanyData(con, ntpMdl.getAcoSid(), ntpMdl.getAbaSid(), dataMdl);

            //ユーザテンプレートデータ取得
            NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);
            NtpTemplateModel tmpMdl = null;
            tmpMdl = biz.getUsrTemplate(
                    con, ntpMdl.getUsrSid());

            if (tmpMdl == null) {
                tmpMdl = new NtpTemplateModel();
                tmpMdl.setNttTemp(GSConstNippou.ITEM_USE);
            }

            //添付ファイル情報取得
            if (tmpMdl.getNttTemp() == GSConstNippou.ITEM_USE) {
                //添付ファイル情報取得
                NtpBinDao binDao = new NtpBinDao(con);
                ArrayList<CmnBinfModel> retBin = binDao.getFileList(ntpMdl.getNipSid());
                dataMdl.setNtp040FileList(retBin);
            }

            //jsonデータ形成
            jsonObject = JSONObject.fromObject(dataMdl);
        }
        return jsonObject;
    }

    /**
     * <br>[機  能] 添付ファイルをテンポラリディレクトリにコピーする
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param binList 添付ファイルリスト
     * @param appRootPath アプリケーションルート
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @param domain ドメイン
     * @throws SQLException SQL実行時例外
     * @throws IOToolsException ファイルアクセス時例外
     * @throws IOException 入出力時例外
     * @throws TempFileException 添付ファイルUtil内での例外
     */
    public void tempFileCopy(List<NtpBinModel> binList,
                                 String appRootPath,
                                 String tempDir,
                                 Connection con,
                                 String domain)
        throws SQLException, IOToolsException, IOException, TempFileException {

        CommonBiz cmnBiz = new CommonBiz();
        UDate now = new UDate();
        String dateStr = now.getDateString();
        int i = 1;
        for (NtpBinModel retBinMdl : binList) {
            CmnBinfModel binMdl = cmnBiz.getBinInfo(con, retBinMdl.getBinSid(), domain);
            if (binMdl != null) {

                //添付ファイルをテンポラリディレクトリにコピーする。
                cmnBiz.saveTempFile(dateStr, binMdl, appRootPath, tempDir, i);
                i++;
            }
        }
    }

    /**
     * <br>[機  能] 添付ファイル情報を取得(json形式)
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param tempDir テンポラリディレクトリ
     * @param con コネクション
     * @throws IOToolsException
     * @throws IOToolsException ファイルアクセス時例外
     * @return jsonTempStr
     */
    public String setTempFiles(String tempDir, Connection con)
        throws IOToolsException {

        String jsonTempStr = null;
        JSONArray jsonTempArray = null;
        CommonBiz commonBiz = new CommonBiz();

        List<LabelValueBean> fileLabels = commonBiz.getTempFileLabelList(tempDir);
        if (!fileLabels.isEmpty()) {
            jsonTempArray = JSONArray.fromObject(fileLabels);
            jsonTempStr = jsonTempArray.toString();
        }

        return jsonTempStr;
    }

    /**
     * <br>[機  能] ユーザ情報取得
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param userSid ユーザSID
     * @return jsonTempStr
     * @throws SQLException SQL実行例外
     */
    public CmnUsrmInfModel getUsrInfo(String userSid)
                                     throws SQLException {

        //インスタンス生成
        CmnUsrmInfDao dao = new CmnUsrmInfDao(con__);
        CmnUsrmInfModel model = new CmnUsrmInfModel();

        //ユーザSIDをセット
        model.setUsrSid(
                Integer.parseInt(
                        NullDefault.getString(userSid, "-1")));

        model = dao.select(model);

        return model;
    }

    /**
     * <br>[機  能] コメントを登録します
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl paramMdl
     * @param ntpSid 日報SID
     * @param commentStr コメント
     * @param userSid 登録者SID
     * @param appPath アプリケーションパス
     * @param pluginConfig プラグインコンフィグ
     * @param reqMdl リクエストモデル
     * @throws Exception SQL実行時例外
     */
    public void insertComment(
                            Ntp040ParamModel paramMdl,
                            int ntpSid,
                            String commentStr,
                            int userSid,
                            String appPath,
                            PluginConfig pluginConfig,
                            RequestModel reqMdl) throws Exception {

        NtpCommentModel npcMdl = new NtpCommentModel();
        NtpCommonBiz cmnBiz = new  NtpCommonBiz(con__, reqMdl__);
        UDate now = new UDate();

        //SID採番
        int npcSid = (int) cntCon__.getSaibanNumber(
                GSConstNippou.SBNSID_NIPPOU_COMMENT,
                GSConstNippou.SBNSID_SUB_NIPPOU_COMMENT, userSid);

        npcMdl.setNpcSid(npcSid);
        npcMdl.setNipSid(ntpSid);
        npcMdl.setUsrSid(userSid);
        npcMdl.setNpcComment(commentStr);
        npcMdl.setNpcViewKbn(0);
        npcMdl.setNpcEdate(now);
        npcMdl.setNpcEuid(userSid);
        npcMdl.setNpcAdate(now);
        npcMdl.setNpcAuid(userSid);

        NtpCommentDao npcDao = new NtpCommentDao(con__);

        //登録
        npcDao.insert(npcMdl);

        //ショートメール通知
        if (paramMdl.getSmailUseOk() == GSConstNippou.PLUGIN_NOT_USE) {
            //ショートメールプラグインが無効の場合、ショートメールを送信しない。
            return;
        }

        //日報データを取得
        NtpDataDao ntpDao = new NtpDataDao(con__);
        NtpDataModel ntpMdl = null;

        ntpMdl = ntpDao.select(Integer.valueOf(ntpSid));

        if (ntpMdl != null) {
            String url = __createNippouUrlDefo(
                    "edit", String.valueOf(ntpSid), String.valueOf(userSid), ntpMdl, paramMdl);
            cmnBiz.sendPlgSmail(
                    con__, cntCon__, ntpMdl, npcMdl, appPath, pluginConfig, url, reqMdl);
        }
    }

    /**
     * <br>[機  能] コメントを削除します
     * <br>[解  説]
     * <br>[備  考]
     * @param npcSid コメントSID
     * @throws Exception SQL実行時例外
     */
    public void deleteComment(int npcSid) throws Exception {

        NtpCommentDao npcDao = new NtpCommentDao(con__);

        //コメント削除
        npcDao.delete(npcSid);
    }

    /**
     * <br>[機  能] コメントを取得します
     * <br>[解  説]
     * <br>[備  考]
     * @param ntpSid 日報SID
     * @throws Exception SQL実行時例外
     * @return jsonData jsonコメントリスト
     */
    public JSONArray getComment(
                            int ntpSid) throws Exception {
        Ntp040Dao cmtDao = new Ntp040Dao(con__);
        ArrayList<Ntp040DspCommentModel> ntpCmtList = null;
        ntpCmtList = cmtDao.getDspNpcList(reqMdl__, ntpSid);
        JSONArray jsonData = new JSONArray();
        jsonData = JSONArray.fromObject(ntpCmtList);
        return jsonData;
    }

    /**
     * <br>[機  能] 日報登録確認URLを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param cmd 処理モード
     * @param ntpSid 日報SID
     * @param usrSid ユーザーSID
     * @param ntpMdl 日報情報
     * @param paramMdl Ntp040ParamModel
     * @return 日報登録確認URL
     * @throws UnsupportedEncodingException URLのエンコードに失敗
     */
    private String __createNippouUrlDefo(String cmd,
                                          String ntpSid, String usrSid,
                                          NtpDataModel ntpMdl,
                                          Ntp040ParamModel paramMdl)
    throws UnsupportedEncodingException {
        String nippouUrl = null;

        String ntpUrl = reqMdl__.getReferer();
        if (!StringUtil.isNullZeroString(ntpUrl)) {
            nippouUrl = ntpUrl.substring(0, ntpUrl.lastIndexOf("/"));
            nippouUrl = nippouUrl.substring(0, nippouUrl.lastIndexOf("/"));
            nippouUrl += "/common/cmn001.do?url=";

            String paramUrl = reqMdl__.getRequestURI();
            paramUrl = paramUrl.substring(0, paramUrl.lastIndexOf("/"));

            String domain = "";
            if (!reqMdl__.getDomain().equals(GSConst.GS_DOMAIN)) {
                domain = reqMdl__.getDomain() + "/";
                paramUrl = paramUrl.replace(
                 GSConstNippou.PLUGIN_ID_NIPPOU, domain + GSConstNippou.PLUGIN_ID_NIPPOU);
            }

            paramUrl += "/ntp040.do";
            paramUrl += "?ntp010SelectDate=" + UDateUtil.getYYMD(ntpMdl.getNipDate());
            paramUrl += "&cmd=" + cmd;
            paramUrl += "&ntp010NipSid=" + ntpMdl.getNipSid();
            paramUrl += "&ntp010SelectUsrSid=" + ntpMdl.getUsrSid();
            paramUrl += "&ntp010SelectUsrKbn=" + "0";
            paramUrl += "&ntp010DspDate=" + UDateUtil.getYYMD(ntpMdl.getNipDate());
            paramUrl += "&dspMod=" + "1";
            paramUrl += "&ntp010DspGpSid=" + paramMdl.getNtp010DspGpSid();
            paramUrl = URLEncoder.encode(paramUrl, Encoding.UTF_8);

            nippouUrl += paramUrl;
        }

        return nippouUrl;
    }

    /**
     * <br>[機  能] 目標データを取得する(テンプレートで登録されているデータ)
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param year 年
     * @param month 月
     * @param nttSid テンプレートSID
     * @param usrSid ユーザーSID
     * @return 日報登録確認URL
     * @throws Exception 実行例外
     */
    public JSONObject getTargetData(Connection con,
                                int year,
                                int month,
                                int usrSid,
                                int nttSid) throws Exception {

        JSONObject json = null;
        Ntp040DspTargetModel dspTrgMdl = null;
        NtpCommonBiz biz = new NtpCommonBiz(con__, reqMdl__);

        //ユーザ適用目標取得
        List<NtpTargetModel> trgList = null;
        trgList = biz.getUsrTmpTarget(
                con, nttSid, usrSid);


        //表示ユーザの表示月の目標取得
        if (trgList != null && !trgList.isEmpty()) {

            List<NtpPriTargetModel> priTrgList = null;
            NtpPriTargetModel priTrgMdl = null;
            NtpPriTargetDao priTrgDao = new NtpPriTargetDao(con);


            priTrgList = new ArrayList<NtpPriTargetModel>();
            for (NtpTargetModel trgMdl : trgList) {

                if (trgMdl != null) {

                    //ユーザデータ取得
                    priTrgMdl = priTrgDao.select(
                                                trgMdl.getNtgSid(),
                                                usrSid,
                                                year,
                                                month);

                    if (priTrgMdl == null) {
                        //データがない場合は一番最近のデータの値を設定
                        priTrgMdl = priTrgDao.getLatelyData(trgMdl.getNtgSid(), usrSid);

                        if (priTrgMdl == null) {
                            //データがない場合はデフォルト値を設定
                            priTrgMdl = new NtpPriTargetModel();
                            //目標SID
                            priTrgMdl.setNtgSid(trgMdl.getNtgSid());
                            //デフォルト値
                            priTrgMdl.setNpgTarget(trgMdl.getNtgDef());
                            //実績
                            priTrgMdl.setNpgRecord(new Long(0));
                        } else {
                            //実績
                            priTrgMdl.setNpgRecord(new Long(0));
                            priTrgMdl.setNpgAdate(null);
                            priTrgMdl.setNpgDate(null);
                            priTrgMdl.setNpgEdate(null);
                        }
                    } else {
                        priTrgMdl.setNpgAdate(null);
                        priTrgMdl.setNpgDate(null);
                        priTrgMdl.setNpgEdate(null);
                    }

                    //名前
                    priTrgMdl.setNpgTargetName(trgMdl.getNtgName());
                    //単位
                    priTrgMdl.setNpgTargetUnit(trgMdl.getNtgUnit());

                    if (priTrgMdl.getNpgRecord() >= priTrgMdl.getNpgTarget()) {
                        //目標を達成している場合
                        priTrgMdl.setNpgTargetKbn(1);
                    } else {
                        priTrgMdl.setNpgTargetKbn(0);
                    }

                    priTrgList.add(priTrgMdl);
                }
            }

            if (!priTrgList.isEmpty()) {
                dspTrgMdl = new Ntp040DspTargetModel();
                dspTrgMdl.setNttSid(nttSid);
                dspTrgMdl.setUsrSid(usrSid);
                dspTrgMdl.setYear(year);
                dspTrgMdl.setMonth(month);
                dspTrgMdl.setNtgList(priTrgList);

                json = new JSONObject();
                json = JSONObject.fromObject(dspTrgMdl);
            }
        }
        return json;
    }

    /**
     * <br>[機  能] 目標データを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param year 年
     * @param month 月
     * @param ntgSid 目標SID
     * @param usrSid ユーザーSID
     * @return 日報登録確認URL
     * @throws Exception 実行例外
     */
    public JSONObject getTargetRecData(Connection con,
                                int year,
                                int month,
                                int usrSid,
                                int ntgSid) throws Exception {

        JSONObject json = null;
        NtpTargetModel trgMdl = null;
        NtpTargetDao trgDao = new NtpTargetDao(con);
        trgMdl = trgDao.select(ntgSid);

        NtpPriTargetModel priTrgMdl = null;
        NtpPriTargetDao priTrgDao = new NtpPriTargetDao(con);

        if (trgMdl != null) {
            //ユーザデータ取得
            priTrgMdl = priTrgDao.select(
                                        trgMdl.getNtgSid(),
                                        usrSid,
                                        year,
                                        month);

            if (priTrgMdl == null) {
                //データがない場合はデフォルト値を設定
                priTrgMdl = new NtpPriTargetModel();
                //目標SID
                priTrgMdl.setNtgSid(trgMdl.getNtgSid());
                //デフォルト値
                priTrgMdl.setNpgTarget(trgMdl.getNtgDef());
                //実績
                priTrgMdl.setNpgRecord(new Long(0));
            } else {
                priTrgMdl.setNpgAdate(null);
                priTrgMdl.setNpgDate(null);
                priTrgMdl.setNpgEdate(null);
            }

            //名前
            priTrgMdl.setNpgTargetName(trgMdl.getNtgName());
            //単位
            priTrgMdl.setNpgTargetUnit(trgMdl.getNtgUnit());

            if (priTrgMdl.getNpgRecord() >= priTrgMdl.getNpgTarget()) {
                //目標を達成している場合
                priTrgMdl.setNpgTargetKbn(1);
            } else {
                priTrgMdl.setNpgTargetKbn(0);
            }

            json = new JSONObject();
            json = JSONObject.fromObject(priTrgMdl);
        }

        return json;
    }

    /**
     * <br>[機  能] 目標を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param param 目標パラメータ
     * @param umodel ユーザモデル
     * @throws SQLException SQL実行時例外
     */
    public void setTarget(Connection con,
            Ntp040TargetParam param,
            BaseUserModel umodel) throws SQLException {


        NtpPriTargetDao dao = new NtpPriTargetDao(con);

        NtpPriTargetModel ptgMdl = new NtpPriTargetModel();

        String record = NullDefault.getString(param.getRecordStr(), "0");
        String target = NullDefault.getString(param.getTargetStr(), "0");

        ptgMdl.setNtgSid(param.getNtgSid());
        ptgMdl.setUsrSid(param.getUsrSid());
        ptgMdl.setNpgRecord(Long.valueOf(record));
        ptgMdl.setNpgTarget(Long.valueOf(target));
        ptgMdl.setNpgYear(param.getYear());
        ptgMdl.setNpgMonth(param.getMonth());

        UDate date = new UDate();
        UDate ptgDate = date.cloneUDate();

        ptgDate.setYear(param.getYear());
        ptgDate.setMonth(param.getMonth() + 1);
        ptgDate.setZeroDdHhMmSs();
        ptgMdl.setNpgDate(ptgDate);

        ptgMdl.setNpgEdate(date);
        ptgMdl.setNpgEuid(umodel.getUsrsid());


        int count = dao.update(ptgMdl);
        if (count <= 0) {

            ptgMdl.setNpgAdate(date);
            ptgMdl.setNpgAuid(umodel.getUsrsid());

            //レコードがない場合は作成
            dao.insert(ptgMdl);
        }
    }

    /**
     * <br>[機  能] 会社情報履歴取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid 選択ユーザSID
     * @param pageNum ページ番号
     * @return JSONObject 目標データ
     * @throws SQLException SQL実行時例外
     */
    public JSONObject getAdrHistoryList(
            Connection con, String usrSid, int pageNum) throws SQLException {

        JSONObject json = new JSONObject();
        Ntp040Dao dao = new Ntp040Dao(con);
        List<Ntp040AddressModel> adrList = null;
        List<Ntp040AddressModel> adrMapList = new ArrayList<Ntp040AddressModel>();
        List<Ntp040AddressModel> adrDataList = new ArrayList<Ntp040AddressModel>();
        Ntp040AddressModel adrMdl = null;


        adrList = dao.getNtpAdrHistory(Integer.valueOf(usrSid));

        if (!adrList.isEmpty()) {

            Map<String, Ntp040AddressModel> adrMap
                        = new LinkedHashMap<String, Ntp040AddressModel>();

            for (Ntp040AddressModel mdl : adrList) {
                String key = mdl.getCompanySid() + "_" + mdl.getCompanyBaseSid();
                if (adrMap.get(key) == null) {
                    adrMap.put(key, mdl);
                }
            }

            Set<String> keySet = adrMap.keySet();
            Iterator<String> keyIte = keySet.iterator();
            while (keyIte.hasNext()) {

                adrMdl = new Ntp040AddressModel();
                String adrkey = (String) keyIte.next();
                adrMdl = adrMap.get(adrkey);

                //会社情報
                AdrCompanyDao companyDao = new AdrCompanyDao(con);
                AdrCompanyModel companyModel = companyDao.select(adrMdl.getCompanySid());

                if (companyModel != null) {
                    adrMdl.setCompanySid(companyModel.getAcoSid());
                    adrMdl.setCompanyCode(companyModel.getAcoCode());
                    adrMdl.setCompanyName(companyModel.getAcoName());
                }

                //会社拠点情報
                AdrCompanyBaseDao companyBaseDao = new AdrCompanyBaseDao(con);
                AdrCompanyBaseModel companyBaseMdl = new AdrCompanyBaseModel();
                companyBaseMdl = companyBaseDao.select(adrMdl.getCompanyBaseSid());
                if (companyBaseMdl != null) {
                    adrMdl.setCompanyBaseSid(companyBaseMdl.getAbaSid());
                    adrMdl.setCompanyBaseName(companyBaseMdl.getAbaName());
                }
                if (!StringUtil.isNullZeroStringSpace(adrMdl.getCompanyName())) {
                    adrMapList.add(adrMdl);
                }
            }

            int maxCount = adrMapList.size();
            int maxPageNum = PageUtil.getPageCount(maxCount, adrHistoryLimit__);
            int offset = 0;

            if (pageNum == 0) {
                pageNum = 1;

            } else if (pageNum > maxPageNum) {
                pageNum = maxPageNum;
            }

            offset = (pageNum - 1) * adrHistoryLimit__;
            int maxRow = adrHistoryLimit__;

            if (offset > 1) {
                maxRow = offset + adrHistoryLimit__;
            }

            if (!adrMapList.isEmpty()) {

                if (maxRow > adrMapList.size()) {
                    maxRow = adrMapList.size();
                }

                for (int i = offset; i < maxRow; i++) {
                    adrDataList.add(adrMapList.get(i));
                }
            }

            if (!adrDataList.isEmpty()) {
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("pageNum", pageNum);
                jsonMap.put("maxpagesize", maxPageNum);
                jsonMap.put("adrList", adrDataList);
                json = JSONObject.fromObject(jsonMap);
            }

        }
        return json;
    }


    /**
     * <br>[機  能] 案件情報履歴取得
     * <br>[解  説]
     * <br>[備  考]
     * @param con コネクション
     * @param usrSid 選択ユーザSID
     * @param pageNum ページ番号
     * @return JSONObject 目標データ
     * @throws SQLException SQL実行時例外
     */
    public JSONObject getAnkenHistoryList(
            Connection con, String usrSid, int pageNum) throws SQLException {

        JSONObject json = new JSONObject();
        Ntp040Dao dao = new Ntp040Dao(con);
        List<NtpAnkenModel> ankenList = null;
        List<NtpAnkenModel> ankenDataList = new ArrayList<NtpAnkenModel>();

        ankenList = dao.getNtpAnkenHistory2(Integer.valueOf(usrSid));

        if (!ankenList.isEmpty()) {

            int maxCount = ankenList.size();
            int maxPageNum = PageUtil.getPageCount(maxCount, ankenHistoryLimit__);
            int offset = 0;

            if (pageNum == 0) {
                pageNum = 1;

            } else if (pageNum > maxPageNum) {
                pageNum = maxPageNum;
            }

            offset = (pageNum - 1) * ankenHistoryLimit__;
            int maxRow = ankenHistoryLimit__;

            if (offset > 1) {
                maxRow = offset + ankenHistoryLimit__;
            }

            if (!ankenList.isEmpty()) {

                if (maxRow > ankenList.size()) {
                    maxRow = ankenList.size();
                }

                for (int i = offset; i < maxRow; i++) {
                    ankenDataList.add(ankenList.get(i));
                }
            }

            if (!ankenDataList.isEmpty()) {
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("pageNum", pageNum);
                jsonMap.put("maxpagesize", maxPageNum);
                jsonMap.put("ankenList", ankenDataList);
                json = JSONObject.fromObject(jsonMap);
            }

        }
        return json;
    }

    /**
     * <br>UDateの曜日定数から曜日文字を取得する
     * @param week UDateの曜日定数

     * @return String 曜日
     */
    public String getStrWeek(int week) {

        GsMessage gsMsg = new GsMessage(reqMdl__);

        String str = "";
        switch (week) {
        case UDate.SUNDAY:
            str = gsMsg.getMessage("cmn.sunday");
            break;
        case UDate.MONDAY:
            str = gsMsg.getMessage("cmn.Monday");
            break;
        case UDate.TUESDAY:
            str = gsMsg.getMessage("cmn.tuesday");
            break;
        case UDate.WEDNESDAY:
            str = gsMsg.getMessage("cmn.wednesday");
            break;
        case UDate.THURSDAY:
            str = gsMsg.getMessage("cmn.thursday");
            break;
        case UDate.FRIDAY:
            str = gsMsg.getMessage("cmn.friday");
            break;
        case UDate.SATURDAY:
            str = gsMsg.getMessage("cmn.saturday");
            break;
        default:
            break;
        }
        return str;
    }

    /**
     * <br>指定日のスケジュールデータを取得する
     * @param schUsrSid スケジュールユーザSID
     * @param con コネクション
     * @param date 日付
     * @return スケジュールデータ
     * @throws SQLException sql実行例外
     */
    public JSONArray getSchData(int schUsrSid,
                                 Connection con,
                                 UDate date) throws SQLException {

        JSONArray json = null;
        ScheduleSearchDao schDao = new ScheduleSearchDao(con);

        int sessionUserSid = reqMdl__.getSmodel().getUsrsid();
        Map<String, String> schDataMap = null;
        List<Map<String, String>> schDataMapList = new ArrayList<Map<String, String>>();

        UDate frDate = date.cloneUDate();
        UDate toDate = date.cloneUDate();
        toDate.setMaxHhMmSs();

        //グループスケジュールを取得
        CmnBelongmDao belongDao = new CmnBelongmDao(con);
        ArrayList<Integer> belongList = belongDao.selectUserBelongGroupSid(schUsrSid);

        ArrayList <SchDataModel> schDataList = schDao.getBelongGroupSchData2(
                belongList,
                -1,
                frDate,
                toDate,
                GSConstSchedule.DSP_MOD_DAY,
                sessionUserSid);

        //スケジュール情報を取得(指定ユーザ)
        ArrayList <SchDataModel> schUsrDataList = schDao.select(
                schUsrSid,
                GSConstSchedule.USER_KBN_USER,
                -1,
                frDate,
                toDate,
                GSConstSchedule.DSP_MOD_DAY,
                sessionUserSid);

        if (schUsrDataList != null && !schUsrDataList.isEmpty()) {
            schDataList.addAll(schUsrDataList);
        }

        if (schDataList != null && !schDataList.isEmpty()) {

            for (SchDataModel schMdl : schDataList) {

                //スケジュールの取り込み権限取得
                if (isTakeInScheduleOk(schMdl , con)) {

                    schDataMap = new HashMap<String, String>();
                    schDataMap.put("scdKbn", String.valueOf(schMdl.getScdUsrKbn()));
                    schDataMap.put("scdSid", String.valueOf(schMdl.getScdSid()));
                    schDataMap.put("scdTitle", String.valueOf(
                            StringUtilHtml.transToHTmlWithWbr(schMdl.getScdTitle(), 15)));


                    if (schMdl.getScdDaily() == GSConstSchedule.TIME_NOT_EXIST) {
                        schDataMap.put("scdTime", "");
                    } else {

                        String timeStr = schMdl.getScdFrDate().getStrHour()
                                       + ":"
                                       + schMdl.getScdFrDate().getStrMinute()
                                       + " - "
                                       + schMdl.getScdToDate().getStrHour()
                                       + ":"
                                       + schMdl.getScdToDate().getStrMinute();
                        schDataMap.put("scdTime", timeStr);
                    }
                    schDataMapList.add(schDataMap);
                }
            }
            if (!schDataMapList.isEmpty()) {
                json = new JSONArray();
                json = JSONArray.fromObject(schDataMapList);
            }
        }
        return json;
    }

    /**
     * <br>スケジュール情報を取得する
     * @param schSid スケジュールSID
     * @param con コネクション

     * @param reqMdl リクエストモデル
     * @return String 曜日
     * @throws SQLException sql実行例外
     */
    public JSONObject getSchSelectData(int schSid,
                                 Connection con,
                                 RequestModel reqMdl) throws SQLException {

        //セッション情報を取得
        HttpSession session = reqMdl.getSession();
        BaseUserModel usModel =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);
        int sessionUsrSid = usModel.getUsrsid(); //セッションユーザSID

        JSONObject json = null;
        ScheduleSearchModel scdMdl = null;
        ScheduleSearchDao schDao = new ScheduleSearchDao(con);
        SchCompanyModel companyModel = null;

        Ntp040DataModel dataMdl = new Ntp040DataModel();

        //スケジュール管理者設定を取得
        scdMdl = schDao.getScheduleData(schSid, GSConstSchedule.SSP_AUTHFILTER_VIEW, sessionUsrSid);

        //会社情報、アドレス帳情報を設定
        SchCompanyDao companyDao = new SchCompanyDao(con);
        List<SchCompanyModel> companyList = companyDao.select(schSid);

        if (!companyList.isEmpty()) {
            companyModel = companyList.get(0);
        }

        if (scdMdl != null) {

            UDate frDate = null;
            UDate toDate = null;

            if (scdMdl.getScdDaily() == GSConstSchedule.TIME_EXIST) {
                //時間指定あり
                frDate = scdMdl.getScdFrDate();
                toDate = scdMdl.getScdToDate();
            } else {
                //時間指定なし
                Ntp010Biz ntp010biz = new Ntp010Biz(con, reqMdl__);
                NtpPriConfModel confMdl = ntp010biz.getPrivateConf(sessionUsrSid, con);

                if (confMdl != null) {
                    frDate = confMdl.getNprIniFrDate();
                    toDate = confMdl.getNprIniToDate();
                } else {
                    frDate = new UDate();
                    toDate = new UDate();
                    frDate.setHour(GSConstNippou.DF_FROM_HOUR);
                    frDate.setMinute(GSConstNippou.DF_FROM_MINUTES);
                    toDate.setHour(GSConstNippou.DF_TO_HOUR);
                    toDate.setMinute(GSConstNippou.DF_TO_MINUTES);
                }
            }

            dataMdl.setFrHour(frDate.getIntHour());
            dataMdl.setNtp040DspFrHour(
                    StringUtil.toDecFormat(frDate.getIntHour(), "00"));

            dataMdl.setFrMin(frDate.getIntMinute());
            dataMdl.setNtp040DspFrMinute(
                    StringUtil.toDecFormat(frDate.getIntMinute(), "00"));

            dataMdl.setToHour(toDate.getIntHour());
            dataMdl.setNtp040DspToHour(
                    StringUtil.toDecFormat(toDate.getIntHour(), "00"));

            dataMdl.setToMin(toDate.getIntMinute());
            dataMdl.setNtp040DspToMinute(
                    StringUtil.toDecFormat(toDate.getIntMinute(), "00"));

            //背景
            dataMdl.setBgcolor(scdMdl.getScdBgcolor());

            //タイトル
            dataMdl.setTitle(scdMdl.getScdTitle());


            //会社情報、アドレス帳情報を設定
            if (companyModel != null) {
                _readCompanyData(
                        con, companyModel.getAcoSid(), companyModel.getAbaSid(), dataMdl);
            }

            json = JSONObject.fromObject(dataMdl);

        }

        return json;
    }

    /**
     * <br>取り込み可能なスケジュールか判定する
     * @param scdMdl スケジュールデータ

     * @param con コネクション
     * @return boolean true:権限あり　false:権限無し
     * @throws SQLException SQL実行時例外
     */
    public boolean isTakeInScheduleOk(
            SchDataModel scdMdl,
            Connection con) throws SQLException {

      //セッションユーザSID
        int sessionUsrSid = reqMdl__.getSmodel().getUsrsid();
        boolean takeIn = false;
        if (scdMdl.getScdUsrKbn() == GSConstSchedule.USER_KBN_GROUP) {
            //グループスケジュール
            if (scdMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
            } else {
                takeIn = true;
            }
        } else {
            //ユーザスケジュール
            if (scdMdl.getScdPublic() == GSConstSchedule.DSP_YOTEIARI) {
                //予定あり
                if (scdMdl.getScdUsrSid() == sessionUsrSid) {
                    takeIn = true;
                } else {
                    takeIn = false;
                }
            } else if (scdMdl.getScdPublic() == GSConstSchedule.DSP_NOT_PUBLIC) {
                //非公開
                if (scdMdl.getScdUsrSid() == sessionUsrSid) {
                    takeIn = true;
                } else {
                    takeIn = false;
                }
            } else if (scdMdl.getScdPublic() == GSConstSchedule.DSP_BELONG_GROUP) {
                //所属グループのみ公開
                GroupBiz gpBiz = new GroupBiz();
                if (gpBiz.isBothBelongGroup(scdMdl.getScdUsrSid(), sessionUsrSid, con)) {
                    takeIn = true;
                } else {
                    takeIn = false;
                }
            } else {
                //公開
                takeIn = true;
            }
        }

        return takeIn;
    }

    /**
     * <br>指定日のプロジェクトTODOデータを取得する
     * @param usrSid UDateの曜日定数
     * @param con コネクション
     * @param date 日付
     * @return String 曜日
     * @throws SQLException sql実行例外
     */
    public JSONArray getPrjData(int usrSid,
                                 Connection con,
                                 UDate date) throws SQLException {
        GsMessage gsMsg = new GsMessage(reqMdl__);
        JSONArray json = null;
        //セッション情報を取得
        HttpSession session = reqMdl__.getSession();
        BaseUserModel buMdl =
            (BaseUserModel) session.getAttribute(GSConst.SESSION_KEY);

        Map<String, String> prjDataMap = null;
        List<Map<String, String>> prjDataMapList = new ArrayList<Map<String, String>>();

        ProjectSearchModel bean = new ProjectSearchModel();
        bean.setUserSid(usrSid);
        //完了プロジェクト表示フラグ
        bean.setEndPrjFlg(false);

        //管理者権限がある場合は全て、ない場合は公開プロジェクトのみ取得
        CommonBiz cmnBiz = new CommonBiz();
        boolean adminUser = cmnBiz.isPluginAdmin(con, buMdl, GSConstProject.PLUGIN_ID_PROJECT);
        int getKbn = ProjectSearchModel.GET_OPEN;
        if (adminUser) {
            getKbn = ProjectSearchModel.GET_ALL;
        }
        bean.setGetKbn(getKbn);

        UDate frDate = null;
        //UDate toDate = null;
        frDate = date;

        String[] userSids = {String.valueOf(usrSid)};

        //担当メンバー
        bean.setMemberSid(userSids);
        //状態From
        bean.setStatusFrom(0);
        //状態To
        bean.setStatusTo(99);
        //実績開始
        bean.setEndFrom(frDate);
        //実績終了
        bean.setEndTo(frDate);
        //検索区分
        bean.setPrjSearchKbn(2);

        ProjectSearchDao psDao = new ProjectSearchDao(con, gsMsg);
        List<ProjectItemModel> prjList = psDao.getTodoList2(bean);

        if (!prjList.isEmpty()) {
            for (ProjectItemModel pim : prjList) {

                UDate prjStartDate = pim.getStartJissekiDate();
                UDate prjEndDate = null;

                if (pim.getEndJissekiDate() != null) {
                    prjEndDate = pim.getEndJissekiDate();
                }

                String startDate = gsMsg.getMessage("cmn.year",
                         new String[] {prjStartDate.getStrYear(), prjStartDate.getStrYear()}
                                    )
                               + prjStartDate.getStrMonth() + gsMsg.getMessage("cmn.month")
                               + prjStartDate.getStrDay() + gsMsg.getMessage("cmn.day");

                String endDate = "";
                if (prjEndDate != null) {
                    endDate = gsMsg.getMessage("cmn.year", new String[] {prjEndDate.getStrYear(),
                            prjEndDate.getStrYear()})
                           + prjEndDate.getStrMonth() + gsMsg.getMessage("cmn.month")
                           + prjEndDate.getStrDay() + gsMsg.getMessage("cmn.day");
                }

                prjDataMap = new HashMap<String, String>();
                prjDataMap.put("prjSid", String.valueOf(pim.getProjectSid()));
                prjDataMap.put("todoSid", String.valueOf(pim.getTodoSid()));
                prjDataMap.put("prjTitle", String.valueOf(pim.getTodoTitle()));
                prjDataMap.put("prjStartDate", startDate);
                prjDataMap.put("prjEndDate", endDate);
                prjDataMapList.add(prjDataMap);
            }
            if (!prjDataMapList.isEmpty()) {
                json = JSONArray.fromObject(prjDataMapList);
            }
        }
        return json;
    }

    /**
     * <br>プロジェクトTODO情報を取得する
     * @param prjSid プロジェクトSID
     * @param todoSid TODOSid
     * @param con コネクション

     * @return String 曜日
     * @throws SQLException sql実行例外
     */
    public JSONObject getPrjSelectData(int prjSid, int todoSid,
                                 Connection con) throws SQLException {

        JSONObject json = null;

        Ntp040DataModel dataMdl = new Ntp040DataModel();

        //TODO状態を取得
        PrjTododataDao ptdDao = new PrjTododataDao(con__);
        PrjTododataModel ptdMdl = ptdDao.select(prjSid, todoSid);


        if (ptdMdl != null) {

            //タイトル
            dataMdl.setTitle(ptdMdl.getPtdTitle());

            json = JSONObject.fromObject(dataMdl);

        }

        return json;
    }
    /**
     * <br>[機  能] 日報をPDF出力します。
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param paramMdl Ntp040ParamModel
     * @param con コネクション
     * @param appRootPath アプリケーションルートパス
     * @param outTempDir テンポラリディレクトパス
     * @return 保存ファイル名
     * @throws IOException IO実行時例外
     *
     */
    public String createPdf(
            Ntp040ParamModel paramMdl,
            Connection con,
            String appRootPath,
            String outTempDir
            ) throws IOException {
        OutputStream oStream = null;


        GsMessage gsMsg = new GsMessage(reqMdl__);
        String outBookName = gsMsg.getMessage("ntp.1") + " "
                + paramMdl.getNtp040UsrName() + " "
                + gsMsg.getMessage("cmn.date4"
                        , new String[] {NullDefault.getString(paramMdl.getNtp040InitYear(), "")
                        , NullDefault.getString(paramMdl.getNtp040InitMonth(), "")
                        , NullDefault.getString(paramMdl.getNtp040InitDay(), "")});
        String encOutBookName = FileNameUtil.getTempFileNameTabReplace(outBookName, ".pdf");
        String tmpOutName = paramMdl.getNtp010SelectDate() + GSConstCommon.ENDSTR_SAVEFILE;
        try {
            IOTools.isDirCheck(outTempDir, true);
            oStream = new FileOutputStream(outTempDir + tmpOutName);
            Ntp040PDFUtil pdfUtil = new Ntp040PDFUtil(reqMdl__);
            pdfUtil.createPdf(paramMdl, appRootPath, oStream);
        } catch (Exception e) {
            log__.error("スケジュールPDF出力に失敗しました。", e);
        } finally {
            if (oStream != null) {
                oStream.flush();
                oStream.close();
            }
        }
        log__.debug("日報PDF出力を終了します。");
        return encOutBookName;
    }
}