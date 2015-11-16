package jp.groupsession.v2.fil.fil100;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.sjts.util.NullDefault;
import jp.co.sjts.util.StringUtil;
import jp.co.sjts.util.date.UDate;
import jp.co.sjts.util.struts.StrutsUtil;
import jp.groupsession.v2.cmn.GSConst;
import jp.groupsession.v2.fil.GSConstFile;
import jp.groupsession.v2.fil.GSValidateFile;
import jp.groupsession.v2.fil.fil090.Fil090ParamModel;
import jp.groupsession.v2.fil.model.FileDspModel;
import jp.groupsession.v2.struts.msg.GsMessage;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.util.LabelValueBean;

/**
 * <br>[機  能] ファイル詳細検索画面のフォーム
 * <br>[解  説]
 * <br>[備  考]
 *
 * @author JTS
 */
public class Fil100ParamModel extends Fil090ParamModel {

    /** 抽出対象　キャビネット */
    private String fil100SltCabinetSid__ = null;
    /** 抽出対象　フォルダ */
    private String fil100ChkTrgFolder__ = "0";
    /** 抽出対象　ファイル */
    private String fil100ChkTrgFile__ = "0";
    /** キーワード AND OR */
    private String fil100SearchMode__ = String.valueOf(GSConstFile.KEY_WORD_KBN_AND);
    /** キーワード検索対象 名前 */
    private String fil100ChkWdTrgName__ = "0";
    /** キーワード検索対象 備考 */
    private String fil100ChkWdTrgBiko__ = "0";
    /** キーワード検索対象 ファイル内容 */
    private String fil100ChkWdTrgText__ = "0";

    /** 更新日　日時指定区分 */
    private String fil100ChkOnOff__ = "0";
    /** 検索結果一覧 */
    private List <FileDspModel> resultList__ = null;
    /** キャビネットラベル */
    private List<LabelValueBean> cabinetLabel__;
    /** 年コンボ */
    private List<LabelValueBean> yearLabel__ = null;
    /** 月コンボ */
    private List<LabelValueBean> monthLabel__ = null;
    /** 日コンボ */
    private List<LabelValueBean> dayLabel__ = null;

    /** 検索実行フラグ */
    private int searchFlg__ = GSConstFile.SEARCH_EXECUTE_TRUE;
    /** ページ1 */
    private int fil100pageNum1__ = 1;
    /** ページ2 */
    private int fil100pageNum2__ = 1;
    /** ページコンボ */
    private List<LabelValueBean> pageList__ = null;
    /** ページ表示フラグ */
    private boolean fil100pageDspFlg__ = false;
    /** 検索警告表示フラグ */
    private int fil100WarnDspFlg__ = 0;
    /** 検索結果件数 */
    private long fil100ResultCount__ = 0;
    /** 検索警告表示OK */
    private int fil100WarnOk__ = 0;

    /** 検索更新開始日付 年 */
    private int fileSearchfromYear__ = 0;
    /** 検索更新開始日付 月 */
    private int fileSearchfromMonth__ = 0;
    /** 検索更新開始日付 日 */
    private int fileSearchfromDay__ = 0;
    /** 検索更新終了日付 年 */
    private int fileSearchtoYear__ = 0;
    /** 検索更新終了日付 月 */
    private int fileSearchtoMonth__ = 0;
    /** 検索更新終了日付 日 */
    private int fileSearchtoDay__ = 0;

    /** ソートキー */
    private int fil100sortKey__ = GSConstFile.SORT_NAME;
    /** オーダーキー */
    private int fil100orderKey__ = GSConstFile.ORDER_KEY_ASC;

    /** バイナリSID */
    private String binSid__ = null;

    /** WEB検索プラグイン使用可否 0=使用 1=未使用 */
    private int fil100searchUse__ = GSConst.PLUGIN_USE;

    /** WEB検索ワード */
    private String fil100WebSearchWord__ = "";
    /** HTML表示用検索ワード */
    private String fil100HtmlSearchWord__ = "";

    /*-- SVパラメータ --*/

    /** キャビネットSID */
    private String fil100SvSltCabinetSid__ = null;
    /** 抽出対象　フォルダ */
    private String fil100SvChkTrgFolder__ = String.valueOf(GSConstFile.GET_TARGET_FOLDER);
    /** 抽出対象　ファイル */
    private String fil100SvChkTrgFile__ = String.valueOf(GSConstFile.GET_TARGET_FILE);
    /** キーワード AND OR */
    private String fil100SvSearchMode__ = String.valueOf(GSConstFile.KEY_WORD_KBN_AND);
    /** キーワード検索対象 名前 */
    private String fil100SvChkWdTrgName__ = String.valueOf(GSConstFile.KEYWORD_TARGET_NAME);
    /** キーワード検索対象 備考 */
    private String fil100SvChkWdTrgBiko__ = String.valueOf(GSConstFile.KEYWORD_TARGET_BIKO);
    /** キーワード検索対象 ファイル内容 */
    private String fil100SvChkWdTrgText__ = String.valueOf(GSConstFile.KEYWORD_TARGET_TEXT);
    /** キーワード */
    private String fil100SvChkWdKeyWord__ = null;
    /** from更新日　年 */
    private int fileSvSearchfromYear__ = 0;
    /** from更新日　月 */
    private int fileSvSearchfromMonth__ = 0;
    /** from更新日　日 */
    private int fileSvSearchfromDay__ = 0;
    /** to  更新日　年 */
    private int fileSvSearchtoYear__ = 0;
    /** to  更新日　月 */
    private int fileSvSearchtoMonth__ = 0;
    /** to  更新日　日 */
    private int fileSvSearchtoDay__ = 0;
    /** 更新日　指定区分 */
    private String fil100SvChkOnOff__ = String.valueOf(GSConstFile.UPDATE_KBN_NO);

    /**
     * 検索条件パラメータをSAVEフィールドへ移行します。
     * <br>[機  能]
     * <br>[解  説]
     * <br>[備  考]
     */
    public void saveSearchParm() {

        fil100SvSltCabinetSid__ = fil100SltCabinetSid__;
        fil100SvChkTrgFolder__ = fil100ChkTrgFolder__;
        fil100SvChkTrgFile__ = fil100ChkTrgFile__;
        fil100SvSearchMode__ = fil100SearchMode__;
        fil100SvChkWdTrgName__ = fil100ChkWdTrgName__;
        fil100SvChkWdTrgBiko__ = fil100ChkWdTrgBiko__;
        fil100SvChkWdTrgText__ = fil100ChkWdTrgText__;
        fil100SvChkWdKeyWord__ = getFilSearchWd();
        fileSvSearchfromYear__ = fileSearchfromYear__;
        fileSvSearchfromMonth__ = fileSearchfromMonth__;
        fileSvSearchfromDay__ = fileSearchfromDay__;
        fileSvSearchtoYear__ = fileSearchtoYear__;
        fileSvSearchtoMonth__ = fileSearchtoMonth__;
        fileSvSearchtoDay__ = fileSearchtoDay__;
        fil100SvChkOnOff__ = NullDefault.getString(fil100ChkOnOff__, "0");
    }

    /**
     * <br>[機  能] 検索条件区分を初期化する
     * <br>[解  説]
     * <br>[備  考]
     */
    public void initSearchKbn() {

        if (fil100SltCabinetSid__ == null) {
            fil100ChkOnOff__ = String.valueOf(GSConstFile.UPDATE_KBN_NO);
            fil100ChkTrgFile__ = String.valueOf(GSConstFile.GET_TARGET_FILE);
            fil100ChkTrgFolder__ = String.valueOf(GSConstFile.GET_TARGET_FOLDER);
            fil100ChkWdTrgName__ = String.valueOf(GSConstFile.KEYWORD_TARGET_NAME);
            fil100ChkWdTrgBiko__ = String.valueOf(GSConstFile.KEYWORD_TARGET_BIKO);
            fil100ChkWdTrgText__ = String.valueOf(GSConstFile.KEYWORD_TARGET_TEXT);
        }
    }

    /**
     * <br>[機  能] 詳細検索画面入力チェック
     * <br>[解  説]
     * <br>[備  考]
     * @param req リクエスト
     * @return エラー
     */
    public ActionErrors validateFil100Check(HttpServletRequest req) {
        ActionErrors errors = new ActionErrors();
        ActionMessage msg = null;

        GsMessage gsMsg = new GsMessage();
        String textKeyword = gsMsg.getMessage(req, "cmn.keyword");
        String textUpFrom = gsMsg.getMessage(req, "fil.85");
        String textUpTo = gsMsg.getMessage(req, "fil.86");
        String textUpFromTo = gsMsg.getMessage(req, "fil.87");
        String textUpFromTo2 = gsMsg.getMessage(req, "fil.88");

        //キーワード
        GSValidateFile.validateTextBoxInput(errors,
                                            getFilSearchWd(),
                                            textKeyword,
                                            GSConstFile.MAX_LENGTH_KEYWORD,
                                            false);
        //対象
        GSValidateFile.validateTarget(errors,
                                      req,
                                      fil100ChkTrgFolder__,
                                      fil100ChkTrgFile__);

        //キーワードが入力されている場合のみ
        if (StringUtil.isNullZeroString(getFilSearchWd()) == false) {
            //検索対象
            GSValidateFile.validateSearchTarget(errors,
                                                req,
                                                fil100ChkWdTrgName__,
                                                fil100ChkWdTrgBiko__,
                                                fil100ChkWdTrgText__);
        }

        int chkOnOff = NullDefault.getInt(fil100ChkOnOff__, 0);
        if (chkOnOff == 0) {
            //-- 更新日時 Fromチェック --

            //日付論理チェック
            UDate fromDate = new UDate();
            fromDate.setTime(0);
            fromDate.setDate(fileSearchfromYear__,
                             fileSearchfromMonth__,
                             fileSearchfromDay__);
            boolean fromDateOk = true;
            if (fromDate.getYear() != fileSearchfromYear__
            || fromDate.getMonth() != fileSearchfromMonth__
            || fromDate.getIntDay() != fileSearchfromDay__) {
                msg = new ActionMessage("error.input.notfound.date", textUpFrom);
                StrutsUtil.addMessage(errors, msg, "fileSearchfromYear");
                fromDateOk = false;
            }

            //-- 投稿者日時 Toチェック --

            //日付論理チェック
            UDate toDate = new UDate();
            toDate.setTime(0);
            toDate.setDate(fileSearchtoYear__, fileSearchtoMonth__, fileSearchtoDay__);
            if (toDate.getYear() != fileSearchtoYear__
            || toDate.getMonth() != fileSearchtoMonth__
            || toDate.getIntDay() != fileSearchtoDay__) {
                msg = new ActionMessage("error.input.notfound.date", textUpTo);
                StrutsUtil.addMessage(errors, msg, "fileSearchtoYear");
            } else if (fromDateOk && fromDate.compareDateYMD(toDate) == UDate.SMALL) {
                msg = new ActionMessage("error.input.comp.text",
                        textUpFromTo,
                        textUpFromTo2);
                StrutsUtil.addMessage(errors, msg, "rsv110Kikan");
            }
        }

        return errors;
    }

    /**
     * <p>fil100pageDspFlg を取得します。
     * @return fil100pageDspFlg
     */
    public boolean isFil100pageDspFlg() {
        return fil100pageDspFlg__;
    }
    /**
     * <p>fil100pageDspFlg をセットします。
     * @param fil100pageDspFlg fil100pageDspFlg
     */
    public void setFil100pageDspFlg(boolean fil100pageDspFlg) {
        fil100pageDspFlg__ = fil100pageDspFlg;
    }
    /**
     * <p>fil100WarnDspFlg を取得します。
     * @return fil100WarnDspFlg
     */
    public int getFil100WarnDspFlg() {
        return fil100WarnDspFlg__;
    }
    /**
     * <p>fil100WarnDspFlg をセットします。
     * @param fil100WarnDspFlg fil100WarnDspFlg
     */
    public void setFil100WarnDspFlg(int fil100WarnDspFlg) {
        this.fil100WarnDspFlg__ = fil100WarnDspFlg;
    }
    /**
     * <p>fil100ResultCount を取得します。
     * @return fil100ResultCount
     */
    public long getFil100ResultCount() {
        return fil100ResultCount__;
    }
    /**
     * <p>fil100ResultCount をセットします。
     * @param fil100ResultCount fil100ResultCount
     */
    public void setFil100ResultCount(long fil100ResultCount) {
        this.fil100ResultCount__ = fil100ResultCount;
    }
    /**
     * <p>fil100WarnOk を取得します。
     * @return fil100WarnOk
     */
    public int getFil100WarnOk() {
        return fil100WarnOk__;
    }
    /**
     * <p>fil100WarnOk をセットします。
     * @param fil100WarnOk fil100WarnOk
     */
    public void setFil100WarnOk(int fil100WarnOk) {
        this.fil100WarnOk__ = fil100WarnOk;
    }
    /**
     * <p>pageList を取得します。
     * @return pageList
     */
    public List<LabelValueBean> getPageList() {
        return pageList__;
    }
    /**
     * <p>pageList をセットします。
     * @param pageList pageList
     */
    public void setPageList(List<LabelValueBean> pageList) {
        pageList__ = pageList;
    }
    /**
     * <p>fil100pageNum1 を取得します。
     * @return fil100pageNum1
     */
    public int getFil100pageNum1() {
        return fil100pageNum1__;
    }
    /**
     * <p>fil100pageNum1 をセットします。
     * @param fil100pageNum1 fil100pageNum1
     */
    public void setFil100pageNum1(int fil100pageNum1) {
        fil100pageNum1__ = fil100pageNum1;
    }
    /**
     * <p>fil100pageNum2 を取得します。
     * @return fil100pageNum2
     */
    public int getFil100pageNum2() {
        return fil100pageNum2__;
    }
    /**
     * <p>fil100pageNum2 をセットします。
     * @param fil100pageNum2 fil100pageNum2
     */
    public void setFil100pageNum2(int fil100pageNum2) {
        fil100pageNum2__ = fil100pageNum2;
    }
    /**
     * <p>searchFlg を取得します。
     * @return searchFlg
     */
    public int getSearchFlg() {
        return searchFlg__;
    }

    /**
     * <p>searchFlg をセットします。
     * @param searchFlg searchFlg
     */
    public void setSearchFlg(int searchFlg) {
        searchFlg__ = searchFlg;
    }
    /**
     * <p>dayLabel を取得します。
     * @return dayLabel
     */
    public List<LabelValueBean> getDayLabel() {
        return dayLabel__;
    }

    /**
     * <p>dayLabel をセットします。
     * @param dayLabel dayLabel
     */
    public void setDayLabel(List<LabelValueBean> dayLabel) {
        dayLabel__ = dayLabel;
    }

    /**
     * <p>monthLabel を取得します。
     * @return monthLabel
     */
    public List<LabelValueBean> getMonthLabel() {
        return monthLabel__;
    }

    /**
     * <p>monthLabel をセットします。
     * @param monthLabel monthLabel
     */
    public void setMonthLabel(List<LabelValueBean> monthLabel) {
        monthLabel__ = monthLabel;
    }

    /**
     * <p>yearLabel を取得します。
     * @return yearLabel
     */
    public List<LabelValueBean> getYearLabel() {
        return yearLabel__;
    }

    /**
     * <p>yearLabel をセットします。
     * @param yearLabel yearLabel
     */
    public void setYearLabel(List<LabelValueBean> yearLabel) {
        yearLabel__ = yearLabel;
    }
    /**
     * <p>cabinetLabel を取得します。
     * @return cabinetLabel
     */
    public List<LabelValueBean> getCabinetLabel() {
        return cabinetLabel__;
    }

    /**
     * <p>cabinetLabel をセットします。
     * @param cabinetLabel cabinetLabel
     */
    public void setCabinetLabel(List<LabelValueBean> cabinetLabel) {
        cabinetLabel__ = cabinetLabel;
    }
    /**
     * <p>resultList を取得します。
     * @return resultList
     */
    public List<FileDspModel> getResultList() {
        return resultList__;
    }

    /**
     * <p>resultList をセットします。
     * @param resultList resultList
     */
    public void setResultList(List<FileDspModel> resultList) {
        resultList__ = resultList;
    }

    /**
     * <p>fil100SvChkWdKeyWord を取得します。
     * @return fil100SvChkWdKeyWord
     */
    public String getFil100SvChkWdKeyWord() {
        return fil100SvChkWdKeyWord__;
    }
    /**
     * <p>fil100SvChkWdKeyWord をセットします。
     * @param fil100SvChkWdKeyWord fil100SvChkWdKeyWord
     */
    public void setFil100SvChkWdKeyWord(String fil100SvChkWdKeyWord) {
        fil100SvChkWdKeyWord__ = fil100SvChkWdKeyWord;
    }
    /**
     * <p>fil100ChkOnOff を取得します。
     * @return fil100ChkOnOff
     */
    public String getFil100ChkOnOff() {
        return fil100ChkOnOff__;
    }
    /**
     * <p>fil100ChkOnOff をセットします。
     * @param fil100ChkOnOff fil100ChkOnOff
     */
    public void setFil100ChkOnOff(String fil100ChkOnOff) {
        fil100ChkOnOff__ = fil100ChkOnOff;
    }
    /**
     * <p>fil100ChkTrgFile を取得します。
     * @return fil100ChkTrgFile
     */
    public String getFil100ChkTrgFile() {
        return fil100ChkTrgFile__;
    }
    /**
     * <p>fil100ChkTrgFile をセットします。
     * @param fil100ChkTrgFile fil100ChkTrgFile
     */
    public void setFil100ChkTrgFile(String fil100ChkTrgFile) {
        fil100ChkTrgFile__ = fil100ChkTrgFile;
    }
    /**
     * <p>fil100ChkTrgFolder を取得します。
     * @return fil100ChkTrgFolder
     */
    public String getFil100ChkTrgFolder() {
        return fil100ChkTrgFolder__;
    }
    /**
     * <p>fil100ChkTrgFolder をセットします。
     * @param fil100ChkTrgFolder fil100ChkTrgFolder
     */
    public void setFil100ChkTrgFolder(String fil100ChkTrgFolder) {
        fil100ChkTrgFolder__ = fil100ChkTrgFolder;
    }
    /**
     * <p>fil100ChkWdTrgBiko を取得します。
     * @return fil100ChkWdTrgBiko
     */
    public String getFil100ChkWdTrgBiko() {
        return fil100ChkWdTrgBiko__;
    }
    /**
     * <p>fil100ChkWdTrgBiko をセットします。
     * @param fil100ChkWdTrgBiko fil100ChkWdTrgBiko
     */
    public void setFil100ChkWdTrgBiko(String fil100ChkWdTrgBiko) {
        fil100ChkWdTrgBiko__ = fil100ChkWdTrgBiko;
    }
    /**
     * <p>fil100ChkWdTrgName を取得します。
     * @return fil100ChkWdTrgName
     */
    public String getFil100ChkWdTrgName() {
        return fil100ChkWdTrgName__;
    }
    /**
     * <p>fil100ChkWdTrgName をセットします。
     * @param fil100ChkWdTrgName fil100ChkWdTrgName
     */
    public void setFil100ChkWdTrgName(String fil100ChkWdTrgName) {
        fil100ChkWdTrgName__ = fil100ChkWdTrgName;
    }
    /**
     * <p>fil100SearchMode を取得します。
     * @return fil100SearchMode
     */
    public String getFil100SearchMode() {
        return fil100SearchMode__;
    }
    /**
     * <p>fil100SearchMode をセットします。
     * @param fil100SearchMode fil100SearchMode
     */
    public void setFil100SearchMode(String fil100SearchMode) {
        fil100SearchMode__ = fil100SearchMode;
    }

    /**
     * <p>fil100SvChkOnOff を取得します。
     * @return fil100SvChkOnOff
     */
    public String getFil100SvChkOnOff() {
        return fil100SvChkOnOff__;
    }
    /**
     * <p>fil100SvChkOnOff をセットします。
     * @param fil100SvChkOnOff fil100SvChkOnOff
     */
    public void setFil100SvChkOnOff(String fil100SvChkOnOff) {
        fil100SvChkOnOff__ = fil100SvChkOnOff;
    }
    /**
     * <p>fil100SvChkTrgFile を取得します。
     * @return fil100SvChkTrgFile
     */
    public String getFil100SvChkTrgFile() {
        return fil100SvChkTrgFile__;
    }
    /**
     * <p>fil100SvChkTrgFile をセットします。
     * @param fil100SvChkTrgFile fil100SvChkTrgFile
     */
    public void setFil100SvChkTrgFile(String fil100SvChkTrgFile) {
        fil100SvChkTrgFile__ = fil100SvChkTrgFile;
    }
    /**
     * <p>fil100SvChkTrgFolder を取得します。
     * @return fil100SvChkTrgFolder
     */
    public String getFil100SvChkTrgFolder() {
        return fil100SvChkTrgFolder__;
    }
    /**
     * <p>fil100SvChkTrgFolder をセットします。
     * @param fil100SvChkTrgFolder fil100SvChkTrgFolder
     */
    public void setFil100SvChkTrgFolder(String fil100SvChkTrgFolder) {
        fil100SvChkTrgFolder__ = fil100SvChkTrgFolder;
    }
    /**
     * <p>fil100SvChkWdTrgBiko を取得します。
     * @return fil100SvChkWdTrgBiko
     */
    public String getFil100SvChkWdTrgBiko() {
        return fil100SvChkWdTrgBiko__;
    }
    /**
     * <p>fil100SvChkWdTrgBiko をセットします。
     * @param fil100SvChkWdTrgBiko fil100SvChkWdTrgBiko
     */
    public void setFil100SvChkWdTrgBiko(String fil100SvChkWdTrgBiko) {
        fil100SvChkWdTrgBiko__ = fil100SvChkWdTrgBiko;
    }
    /**
     * <p>fil100SvChkWdTrgName を取得します。
     * @return fil100SvChkWdTrgName
     */
    public String getFil100SvChkWdTrgName() {
        return fil100SvChkWdTrgName__;
    }
    /**
     * <p>fil100SvChkWdTrgName をセットします。
     * @param fil100SvChkWdTrgName fil100SvChkWdTrgName
     */
    public void setFil100SvChkWdTrgName(String fil100SvChkWdTrgName) {
        fil100SvChkWdTrgName__ = fil100SvChkWdTrgName;
    }
    /**
     * <p>fil100SvSearchMode を取得します。
     * @return fil100SvSearchMode
     */
    public String getFil100SvSearchMode() {
        return fil100SvSearchMode__;
    }
    /**
     * <p>fil100SvSearchMode をセットします。
     * @param fil100SvSearchMode fil100SvSearchMode
     */
    public void setFil100SvSearchMode(String fil100SvSearchMode) {
        fil100SvSearchMode__ = fil100SvSearchMode;
    }
    /**
     * <p>fil100SvSltCabinetSid を取得します。
     * @return fil100SvSltCabinetSid
     */
    public String getFil100SvSltCabinetSid() {
        return fil100SvSltCabinetSid__;
    }
    /**
     * <p>fil100SvSltCabinetSid をセットします。
     * @param fil100SvSltCabinetSid fil100SvSltCabinetSid
     */
    public void setFil100SvSltCabinetSid(String fil100SvSltCabinetSid) {
        fil100SvSltCabinetSid__ = fil100SvSltCabinetSid;
    }

    /**
     * <p>fileSearchfromDay を取得します。
     * @return fileSearchfromDay
     */
    public int getFileSearchfromDay() {
        return fileSearchfromDay__;
    }

    /**
     * <p>fileSearchfromDay をセットします。
     * @param fileSearchfromDay fileSearchfromDay
     */
    public void setFileSearchfromDay(int fileSearchfromDay) {
        fileSearchfromDay__ = fileSearchfromDay;
    }

    /**
     * <p>fileSearchfromMonth を取得します。
     * @return fileSearchfromMonth
     */
    public int getFileSearchfromMonth() {
        return fileSearchfromMonth__;
    }

    /**
     * <p>fileSearchfromMonth をセットします。
     * @param fileSearchfromMonth fileSearchfromMonth
     */
    public void setFileSearchfromMonth(int fileSearchfromMonth) {
        fileSearchfromMonth__ = fileSearchfromMonth;
    }

    /**
     * <p>fileSearchfromYear を取得します。
     * @return fileSearchfromYear
     */
    public int getFileSearchfromYear() {
        return fileSearchfromYear__;
    }

    /**
     * <p>fileSearchfromYear をセットします。
     * @param fileSearchfromYear fileSearchfromYear
     */
    public void setFileSearchfromYear(int fileSearchfromYear) {
        fileSearchfromYear__ = fileSearchfromYear;
    }

    /**
     * <p>fileSearchtoDay を取得します。
     * @return fileSearchtoDay
     */
    public int getFileSearchtoDay() {
        return fileSearchtoDay__;
    }

    /**
     * <p>fileSearchtoDay をセットします。
     * @param fileSearchtoDay fileSearchtoDay
     */
    public void setFileSearchtoDay(int fileSearchtoDay) {
        fileSearchtoDay__ = fileSearchtoDay;
    }

    /**
     * <p>fileSearchtoMonth を取得します。
     * @return fileSearchtoMonth
     */
    public int getFileSearchtoMonth() {
        return fileSearchtoMonth__;
    }

    /**
     * <p>fileSearchtoMonth をセットします。
     * @param fileSearchtoMonth fileSearchtoMonth
     */
    public void setFileSearchtoMonth(int fileSearchtoMonth) {
        fileSearchtoMonth__ = fileSearchtoMonth;
    }

    /**
     * <p>fileSearchtoYear を取得します。
     * @return fileSearchtoYear
     */
    public int getFileSearchtoYear() {
        return fileSearchtoYear__;
    }

    /**
     * <p>fileSearchtoYear をセットします。
     * @param fileSearchtoYear fileSearchtoYear
     */
    public void setFileSearchtoYear(int fileSearchtoYear) {
        fileSearchtoYear__ = fileSearchtoYear;
    }

    /**
     * <p>fil100orderKey を取得します。
     * @return fil100orderKey
     */
    public int getFil100orderKey() {
        return fil100orderKey__;
    }

    /**
     * <p>fil100orderKey をセットします。
     * @param fil100orderKey fil100orderKey
     */
    public void setFil100orderKey(int fil100orderKey) {
        fil100orderKey__ = fil100orderKey;
    }

    /**
     * <p>fil100sortKey を取得します。
     * @return fil100sortKey
     */
    public int getFil100sortKey() {
        return fil100sortKey__;
    }

    /**
     * <p>fil100sortKey をセットします。
     * @param fil100sortKey fil100sortKey
     */
    public void setFil100sortKey(int fil100sortKey) {
        fil100sortKey__ = fil100sortKey;
    }

    /**
     * <p>fileSvSearchfromDay を取得します。
     * @return fileSvSearchfromDay
     */
    public int getFileSvSearchfromDay() {
        return fileSvSearchfromDay__;
    }

    /**
     * <p>fileSvSearchfromDay をセットします。
     * @param fileSvSearchfromDay fileSvSearchfromDay
     */
    public void setFileSvSearchfromDay(int fileSvSearchfromDay) {
        fileSvSearchfromDay__ = fileSvSearchfromDay;
    }

    /**
     * <p>fileSvSearchfromMonth を取得します。
     * @return fileSvSearchfromMonth
     */
    public int getFileSvSearchfromMonth() {
        return fileSvSearchfromMonth__;
    }

    /**
     * <p>fileSvSearchfromMonth をセットします。
     * @param fileSvSearchfromMonth fileSvSearchfromMonth
     */
    public void setFileSvSearchfromMonth(int fileSvSearchfromMonth) {
        fileSvSearchfromMonth__ = fileSvSearchfromMonth;
    }

    /**
     * <p>fileSvSearchfromYear を取得します。
     * @return fileSvSearchfromYear
     */
    public int getFileSvSearchfromYear() {
        return fileSvSearchfromYear__;
    }

    /**
     * <p>fileSvSearchfromYear をセットします。
     * @param fileSvSearchfromYear fileSvSearchfromYear
     */
    public void setFileSvSearchfromYear(int fileSvSearchfromYear) {
        fileSvSearchfromYear__ = fileSvSearchfromYear;
    }

    /**
     * <p>fileSvSearchtoDay を取得します。
     * @return fileSvSearchtoDay
     */
    public int getFileSvSearchtoDay() {
        return fileSvSearchtoDay__;
    }

    /**
     * <p>fileSvSearchtoDay をセットします。
     * @param fileSvSearchtoDay fileSvSearchtoDay
     */
    public void setFileSvSearchtoDay(int fileSvSearchtoDay) {
        fileSvSearchtoDay__ = fileSvSearchtoDay;
    }

    /**
     * <p>fileSvSearchtoMonth を取得します。
     * @return fileSvSearchtoMonth
     */
    public int getFileSvSearchtoMonth() {
        return fileSvSearchtoMonth__;
    }

    /**
     * <p>fileSvSearchtoMonth をセットします。
     * @param fileSvSearchtoMonth fileSvSearchtoMonth
     */
    public void setFileSvSearchtoMonth(int fileSvSearchtoMonth) {
        fileSvSearchtoMonth__ = fileSvSearchtoMonth;
    }

    /**
     * <p>fileSvSearchtoYear を取得します。
     * @return fileSvSearchtoYear
     */
    public int getFileSvSearchtoYear() {
        return fileSvSearchtoYear__;
    }

    /**
     * <p>fileSvSearchtoYear をセットします。
     * @param fileSvSearchtoYear fileSvSearchtoYear
     */
    public void setFileSvSearchtoYear(int fileSvSearchtoYear) {
        fileSvSearchtoYear__ = fileSvSearchtoYear;
    }
    /**
     * <p>binSid を取得します。
     * @return binSid
     */
    public String getBinSid() {
        return binSid__;
    }

    /**
     * <p>binSid をセットします。
     * @param binSid binSid
     */
    public void setBinSid(String binSid) {
        binSid__ = binSid;
    }

    /**
     * <p>fil100SltCabinetSid を取得します。
     * @return fil100SltCabinetSid
     */
    public String getFil100SltCabinetSid() {
        return fil100SltCabinetSid__;
    }

    /**
     * <p>fil100SltCabinetSid をセットします。
     * @param fil100SltCabinetSid fil100SltCabinetSid
     */
    public void setFil100SltCabinetSid(String fil100SltCabinetSid) {
        fil100SltCabinetSid__ = fil100SltCabinetSid;
    }

    /**
     * <p>fil100searchUse を取得します。
     * @return fil100searchUse
     */
    public int getFil100searchUse() {
        return fil100searchUse__;
    }

    /**
     * <p>fil100searchUse をセットします。
     * @param fil100searchUse fil100searchUse
     */
    public void setFil100searchUse(int fil100searchUse) {
        fil100searchUse__ = fil100searchUse;
    }

    /**
     * <p>fil100WebSearchWord を取得します。
     * @return fil100WebSearchWord
     */
    public String getFil100WebSearchWord() {
        return fil100WebSearchWord__;
    }

    /**
     * <p>fil100WebSearchWord をセットします。
     * @param fil100WebSearchWord fil100WebSearchWord
     */
    public void setFil100WebSearchWord(String fil100WebSearchWord) {
        fil100WebSearchWord__ = fil100WebSearchWord;
    }

    /**
     * <p>fil100HtmlSearchWord を取得します。
     * @return fil100HtmlSearchWord
     */
    public String getFil100HtmlSearchWord() {
        return fil100HtmlSearchWord__;
    }

    /**
     * <p>fil100HtmlSearchWord をセットします。
     * @param fil100HtmlSearchWord fil100HtmlSearchWord
     */
    public void setFil100HtmlSearchWord(String fil100HtmlSearchWord) {
        fil100HtmlSearchWord__ = fil100HtmlSearchWord;
    }

    /**
     * @return fil100ChkWdTrgText
     */
    public String getFil100ChkWdTrgText() {
        return fil100ChkWdTrgText__;
    }

    /**
     * @param fil100ChkWdTrgText セットする fil100ChkWdTrgText
     */
    public void setFil100ChkWdTrgText(String fil100ChkWdTrgText) {
        fil100ChkWdTrgText__ = fil100ChkWdTrgText;
    }

    /**
     * @return fil100SvChkWdTrgText
     */
    public String getFil100SvChkWdTrgText() {
        return fil100SvChkWdTrgText__;
    }

    /**
     * @param fil100SvChkWdTrgText セットする fil100SvChkWdTrgText
     */
    public void setFil100SvChkWdTrgText(String fil100SvChkWdTrgText) {
        fil100SvChkWdTrgText__ = fil100SvChkWdTrgText;
    }
}