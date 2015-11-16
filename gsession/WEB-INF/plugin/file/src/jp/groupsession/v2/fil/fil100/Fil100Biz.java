package jp.groupsession.v2.fil.fil100;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.PageUtil;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.StringUtilHtml;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.date.UDateUtil;
import jp.co.sjts.util.io.IOToolsException;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.dao.BaseUserModel;
import jp.groupsession.v2.cmn.model.RequestModel;
import jp.groupsession.v2.fil.FilCommonBiz;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.dao.FileAconfDao;
import jp.groupsession.v2.fil.dao.FileSearchDao;
import jp.groupsession.v2.fil.fil010.Fil010Biz;
import jp.groupsession.v2.fil.fil010.FileCabinetDspModel;
import jp.groupsession.v2.fil.model.FileAconfModel;
import jp.groupsession.v2.fil.model.FileDspModel;
import jp.groupsession.v2.fil.model.FileModel;
import jp.groupsession.v2.fil.model.FileUconfModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ファイル詳細検索画面で使用するビジネスロジッククラス
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil100Biz {

    /** Logging インスタンス */
    private static Log log__ = LogFactory.getLog(Fil100Biz.class);
    /** コネクション */
    protected Connection con_ = null;
    /** リクエスト情報 */
    private RequestModel reqMdl__ = null;

    /**
     * <br>[機  能] コンストラクタ
     * <br>[解  説]
     * <br>[備  考]
     *
     * @param reqMdl RequestModel
     * @param con コネクション
     */
    public Fil100Biz(Connection con, RequestModel reqMdl) {
        reqMdl__ = reqMdl;
        con_ = con;
    }

    /**
     * <br>[機  能] 初期表示情報を設定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param usModel セッションユーザ情報
     * @param appRootPath アプリケーションルートパス
     * @return errors エラー
     * @throws SQLException SQL実行例外
     * @throws IOToolsException ファイルアクセス時例外
     */
    public ActionErrors setInitData(
            Fil100ParamModel paramMdl,
            Connection con,
            BaseUserModel usModel,
            String appRootPath)
            throws SQLException, IOToolsException {

        //コンボを設定する
        setCombo(paramMdl, con, usModel);

        //WEB検索のキーワードとHTML表示用のキーワードを設定する。
        String key = paramMdl.getFilSearchWd();
        String webSearchWord = "";
        String htmlSearchWord = "";
        if (!StringUtil.isNullZeroStringSpace(key)) {
            webSearchWord = StringUtil.toSingleCortationEscape(key);
            htmlSearchWord = StringUtilHtml.transToHTmlPlusAmparsant(key);
        }
        paramMdl.setFil100WebSearchWord(webSearchWord);
        paramMdl.setFil100HtmlSearchWord(htmlSearchWord);

        //検索
        return __getFileSrchResult(paramMdl, con, usModel, appRootPath);
    }

    /**
     * <br>[機  能] 検索警告を表示するか判定する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param usModel セッションユーザ情報
     * @param appRootPath アプリケーションルートパス
     * @return true:表示する false:表示しない
     * @throws SQLException 実行例外
     */
    public boolean isDspWarn(
            Fil100ParamModel paramMdl,
            Connection con,
            BaseUserModel usModel,
            String appRootPath) throws SQLException {

        boolean warn = false;

        //管理者設定を取得
        FileAconfDao aconfDao = new FileAconfDao(con);
        FileAconfModel aconf = aconfDao.select();
        if (aconf.getFacWarnCnt() <= 0) {
            //設定件数0件未満の場合は警告を表示しない
            return warn;
        }

        //パラメータモデルを作成する
        int userSid = usModel.getUsrsid();
        Fil100SearchParameterModel prmModel =
                __getSvSearchParameter(paramMdl, userSid, appRootPath);

        //件数カウント
        long maxCount = 0;
        FileSearchDao fsDao = new FileSearchDao(con, reqMdl__);
        maxCount = fsDao.recordCount(prmModel, usModel);
        paramMdl.setFil100ResultCount(maxCount);

        //警告表示判定
        if (maxCount > aconf.getFacWarnCnt()) {
            warn = true;
        }

        return warn;
    }

    /**
     * <br>[機  能] コンボを設定します。
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param umodel セッションユーザ情報
     * @throws SQLException SQL実行時例外
     */
    public void setCombo(Fil100ParamModel paramMdl,
                         Connection con,
                         BaseUserModel umodel)
        throws SQLException {

        Fil010Biz biz = new Fil010Biz(reqMdl__);
        ArrayList<FileCabinetDspModel> ret = biz.getAccessCabinetList(umodel, con, true);

        //キャビネットコンボをセットする
        paramMdl.setCabinetLabel(getCabinetDspLabel(ret));

        //年月日コンボの値をセット
        UDate now = new UDate();
        paramMdl.setYearLabel(getYearList(now.getYear(), reqMdl__));
        paramMdl.setMonthLabel(getMonthList(reqMdl__));
        paramMdl.setDayLabel(getDayList(reqMdl__));

        if (NullDefault.getInt(paramMdl.getFil100ChkOnOff(), -1) == GSConstFile.UPDATE_KBN_NO) {
            //更新日時Toに現在日時を設定
            paramMdl.setFileSearchtoYear(now.getYear());
            paramMdl.setFileSearchtoMonth(now.getMonth());
            paramMdl.setFileSearchtoDay(now.getIntDay());
            //1週間前をFromに設定
            now.addDay(-7);
            paramMdl.setFileSearchfromYear(now.getYear());
            paramMdl.setFileSearchfromMonth(now.getMonth());
            paramMdl.setFileSearchfromDay(now.getIntDay());
        }

    }

    /**
     * <br>[機  能] ファイル検索結果を取得する(検索を行う)
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param con コネクション
     * @param usModel セッションユーザ情報
     * @param appRootPath アプリケーションルートパス
     * @return エラー
     * @throws SQLException SQL実行例外
     */
    private ActionErrors __getFileSrchResult(Fil100ParamModel paramMdl,
                                             Connection con,
                                             BaseUserModel usModel,
                                             String appRootPath)
    throws SQLException {

        ActionErrors errors = new ActionErrors();

        int userSid = usModel.getUsrsid();
        //パラメータモデルを作成する
        Fil100SearchParameterModel prmModel =
                __getSvSearchParameter(paramMdl, userSid, appRootPath);

        FilCommonBiz biz = new FilCommonBiz(con_, reqMdl__);
        FileUconfModel fuMdl = new FileUconfModel();
        //ファイル管理個人設定から最大表示件数を取得する
        fuMdl = biz.getUserConf(userSid, con);

        //1ページ表示件数を取得
        int limit = fuMdl.getFucRirekiCnt();
        prmModel.setLimit(limit);

        long maxCount = 0;
        //件数カウント
        if (paramMdl.getFil100WarnOk() == 1) {
            //件数警告表示でOKの場合
            maxCount = paramMdl.getFil100ResultCount();
        } else {
            FileSearchDao fsDao = new FileSearchDao(con, reqMdl__);
            maxCount = fsDao.recordCount(prmModel, usModel);
        }
        log__.debug("表示件数 = " + maxCount);

        //現在ページ（ページコンボのvalueを設定）
        int nowPage = paramMdl.getFil100pageNum1();
        //結果取得開始カーソル位置
        int start = PageUtil.getRowNumber(nowPage, limit);
        //ページあふれ制御
        int maxPageNum = PageUtil.getPageCount(maxCount, limit);
        if (maxPageNum > 1) {
            paramMdl.setFil100pageDspFlg(true);
        }

        int maxPageStartRow = PageUtil.getRowNumber(maxPageNum, limit);
        if (maxPageStartRow < start) {
            nowPage = maxPageNum;
            start = maxPageStartRow;
        }
        prmModel.setStart(start);

        //ページング
        paramMdl.setFil100pageNum1(nowPage);
        paramMdl.setFil100pageNum2(nowPage);
        paramMdl.setPageList(PageUtil.createPageOptions(maxCount, limit));

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textFolderData = gsMsg.getMessage("fil.89");

        //検索件数0の時のエラーメッセージ表示
        if (maxCount < 1) {
            ActionMessage msg = new ActionMessage("search.data.notfound",
                                                   textFolderData);
            StrutsUtil.addMessage(errors, msg, "search.data.notfound");
            return errors;
        }

        //検索結果取得
        List<FileModel> resList = __getFilSearchResult(con, prmModel, usModel);
        FileDspModel dspData = null;

        //表示用ModelList
        List<FileDspModel> dspList = new ArrayList<FileDspModel>();

        for (FileModel fileModel : resList) {
            dspData = new FileDspModel();
            dspData.setFdrName(fileModel.getFdrName());
            dspData.setFdrSid(fileModel.getFdrSid());
            dspData.setFcbSid(fileModel.getFcbSid());
            dspData.setFdrParentSid(fileModel.getFdrParentSid());
            dspData.setFflFileSize(fileModel.getFflFileSizeStr());
            //更新通知
            if (!(String.valueOf(fileModel.getUsrSid()).equals("0"))) {
                dspData.setCallOn(GSConstFile.CALL_ON);
            }
            dspData.setFdrEdate(UDateUtil.getSlashYYMD(fileModel.getFdrEdate())
                     + " " + UDateUtil.getSeparateHMS(fileModel.getFdrEdate()));
            dspData.setUsrSei(fileModel.getUsrSei());
            dspData.setUsrMei(fileModel.getUsrMei());
            dspData.setFdrKbn(fileModel.getFdrKbn());
            dspData.setBinSid(fileModel.getBinSid());
            dspData.setUsrJKbn(fileModel.getUsrJKbn());
            dspList.add(dspData);
        }

        paramMdl.setResultList(dspList);

        return errors;
    }

    /**
     * <br>[機  能] パラメータモデルを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param paramMdl パラメータモデル
     * @param userSid ユーザSID
     * @param appRootPath アプリケーションルートパス
     * @return Ctr010SearchParameterModel
     */
    private Fil100SearchParameterModel __getSvSearchParameter(
            Fil100ParamModel paramMdl, int userSid, String appRootPath) {

        Fil100SearchParameterModel prmModel = new Fil100SearchParameterModel();

        //全文検索使用フラグ
        prmModel.setSearchFlg(FilCommonBiz.isUseAllTextSearch(appRootPath));

        //ユーザSID
        prmModel.setUsrSid(userSid);

        //セーブ 検索キーワード
        String searchWord = NullDefault.getStringZeroLength(
                paramMdl.getFil100SvChkWdKeyWord(), paramMdl.getFilSearchWd());
        //セーブ 検索対象
        String targetName = paramMdl.getFil100SvChkWdTrgName();
        String targetBiko = paramMdl.getFil100SvChkWdTrgBiko();
        String targetText = paramMdl.getFil100SvChkWdTrgText();
        boolean targName = false;
        boolean targBiko = false;
        boolean targText = false;

        if (String.valueOf(GSConstFile.KEYWORD_TARGET_NAME).equals(targetName)) {
            targName = true;
            prmModel.setTextWordName(searchWord);
        }

        if (String.valueOf(GSConstFile.KEYWORD_TARGET_BIKO).equals(targetBiko)) {
            targBiko = true;
            prmModel.setTextWordBiko(searchWord);
        }

        //全文検索使用時
        if (String.valueOf(GSConstFile.KEYWORD_TARGET_TEXT).equals(targetText)
                && FilCommonBiz.isUseAllTextSearch(appRootPath)) {
            targText = true;
            prmModel.setTextWordText(searchWord);
        }

        if (!paramMdl.getFil100SvChkOnOff().equals(String.valueOf(GSConstFile.UPDATE_KBN_NO))) {
            if (paramMdl.getFileSvSearchfromYear() != 0
                    && paramMdl.getFileSvSearchfromMonth() != 0
                    && paramMdl.getFileSvSearchfromDay() != 0) {

                //From
                UDate dateFr = new UDate();
                dateFr.setDate(paramMdl.getFileSvSearchfromYear(),
                        paramMdl.getFileSvSearchfromMonth(),
                                   paramMdl.getFileSvSearchfromDay());
                dateFr.setZeroHhMmSs();
                prmModel.setUpFrDate(dateFr);

            }

            if (paramMdl.getFileSvSearchtoYear() != 0
                    && paramMdl.getFileSvSearchtoMonth() != 0
                    && paramMdl.getFileSvSearchtoDay() != 0) {

                //To
                UDate dateTo = new UDate();
                dateTo.setDate(paramMdl.getFileSvSearchtoYear(),
                        paramMdl.getFileSvSearchtoMonth(),
                        paramMdl.getFileSvSearchtoDay());
                prmModel.setUpToDate(dateTo);
                dateTo.setMaxHhMmSs();
            }
        }

        prmModel.setWordKbn(Integer.parseInt(paramMdl.getFil100SvSearchMode()));
        prmModel.setSrchTargetName(targName);
        prmModel.setSrchTargetBiko(targBiko);

        //全文検索使用時
        if (FilCommonBiz.isUseAllTextSearch(appRootPath)) {
            prmModel.setSrchTargetText(targText);
        }

        prmModel.setTargetFolder(paramMdl.getFil100SvChkTrgFolder());
        prmModel.setTargetFile(paramMdl.getFil100SvChkTrgFile());
        prmModel.setUpdateKbn(Integer.parseInt(paramMdl.getFil100SvChkOnOff()));
        prmModel.setSearchSortKey(paramMdl.getFil100sortKey());
        prmModel.setSearchOrderKey(paramMdl.getFil100orderKey());

        if (!StringUtil.isNullZeroString(paramMdl.getFil100SvSltCabinetSid())) {
            prmModel.setCabinet(Integer.parseInt(paramMdl.getFil100SvSltCabinetSid()));
        } else {
            if (!StringUtil.isNullZeroString(paramMdl.getFil010SelectCabinet())) {

                paramMdl.setFil100SvSltCabinetSid(paramMdl.getFil010SelectCabinet());
                paramMdl.setFil100SltCabinetSid(paramMdl.getFil010SelectCabinet());
                prmModel.setCabinet(Integer.parseInt(paramMdl.getFil100SvSltCabinetSid()));
            } else {
                paramMdl.setFil100SvSltCabinetSid("-1");
                prmModel.setCabinet(Integer.parseInt(paramMdl.getFil100SvSltCabinetSid()));
            }
        }

        return prmModel;
    }

    /**
     * <br>[機  能] ファイル詳細検索結果取得
     * <br>[解  説]
     * <br>[備  考]
     * @param prmModel パラメータモデル
     * @param con コネクション
     * @param usModel セッションユーザ情報
     * @return 件数
     * @throws SQLException SQL実行時例外
     */
    private List<FileModel> __getFilSearchResult(Connection con,
            Fil100SearchParameterModel prmModel,
            BaseUserModel usModel) throws SQLException {

        FileSearchDao searchDao = new FileSearchDao(con, reqMdl__);
        return searchDao.getSearchData(prmModel, usModel);
    }

    /**
     * <br>[機  能] ファイル詳細検索のキャビネットコンボを取得する
     * <br>[解  説]
     * <br>[備  考]
     * @param cabList キャビネット
     * @return List in LabelValueBean
     * @throws SQLException SQL実行時例外
     */

    public List<LabelValueBean> getCabinetDspLabel(
            ArrayList<FileCabinetDspModel> cabList)
    throws SQLException {

        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl__);
        String textAll = gsMsg.getMessage("cmn.all");

        labelList.add(new LabelValueBean(textAll, "-1"));

        for (FileCabinetDspModel fcdMdl : cabList) {
            labelList.add(new LabelValueBean(
                      fcdMdl.getFcbName(), String.valueOf(fcdMdl.getFcbSid())));
        }

        return labelList;
    }

    /**
     * <br>表示開始日から前6年のコンボを生成します
     * @param year 基準年
     * @param reqMdl RequestModel
     * @return ArrayList (in LabelValueBean)  年コンボ
     */
    public static List<LabelValueBean> getYearList(int year, RequestModel reqMdl) {
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);

        for (int i = year - 6; i <= year; i++) {
            labelList.add(
                    new LabelValueBean(
                        gsMsg.getMessage("cmn.year",
                                new String[] {String.valueOf(i)}), String.valueOf(i)));
        }
        return labelList;
    }
    /**
     * <br>月コンボを生成します
     * @param reqMdl RequestModel
     * @return ArrayList (in LabelValueBean)  月コンボ
     */
    public static List<LabelValueBean> getMonthList(RequestModel reqMdl) {
        int month = 1;
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textMonth = gsMsg.getMessage("cmn.month");

        for (int i = 0; i < 12; i++) {
            labelList.add(
                    new LabelValueBean(month + textMonth, String.valueOf(month)));
            month++;
        }
        return labelList;
    }

    /**
     * <br>日コンボを生成します
     * @param reqMdl RequestModel
     * @return ArrayList (in LabelValueBean)  日コンボ
     */
    public static List<LabelValueBean> getDayList(RequestModel reqMdl) {
        int day = 1;
        List<LabelValueBean> labelList = new ArrayList<LabelValueBean>();

        GsMessage gsMsg = new GsMessage(reqMdl);
        String textDay = gsMsg.getMessage("cmn.day");

        for (int i = 0; i < 31; i++) {
            labelList.add(
                    new LabelValueBean(day + textDay, String.valueOf(day)));
            day++;
        }
        return labelList;
    }
 }